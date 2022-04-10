from io import IncrementalNewlineDecoder
from bs4 import BeautifulSoup
import requests
from selenium.webdriver.common.action_chains import ActionChains
from selenium import webdriver
from constant import *
import pandas as pd
import yaml
import time
import os
import shutil
import glob
page_begin = 475000
page_end = 476517+1
login_URLS = "https://auth.bstock.com/oauth2/authorize?client_id=1b094c5f-c8a6-416c-8c62-4dc77ca88ce9&redirect_uri=https%3A%2F%2Fbstock.com%2Fsupplystore%2Fsso%2Findex%2Flogin%2F&scope=offline_access&response_type=code&state=isRedirect&mp_logo=https%3A%2F%2Fbstock.com%2Fsupplystore%2Fskin%2Ffrontend%2Fbstock%2Fauction%2Fimages%2Flogo.png&site_abb=sup&_ga=2.134834098.256529320.1647985524-786391276.1647985524"
laptop_URLS = "https://bstock.com/supplystore/auction/auction/view/id/"

laptop_keyWord = ['samsung monitor','pc monitor','lenovo monitor','led monitor', 'monitor', 'computer monitor',
                  'lenovo gaming','tablets','lenovo yoga','lenovo laptop',
                  'computer','lenovo ideapad','ideapad','lenovo thinkpad','thinkpad','thinkbook',
                  'notebook','thinkvision','flat panel', 'laptop']
item_running = []
item_without_money = [] 
item_cancel = []
text_not_found = []
blacklist = ["ipad","heart rate monitor"]
laptop_titles = ["id",
        "date",
        "title",
        "bid_count", 
        "number_of_bidders",
        "map_price",
        "current_price",
        "percentage",
        "product_condition",
        "units",
        "city",
        "warehouse",
        "item_location_state",
        "item_location_country"]

def driver_login(driver):
    conf = yaml.load(open('C:/Users/garys/Desktop/WebApps/B_Stock_Auction/login.yml'))
    username = conf['user']['email']
    password = conf['user']['password']
    driver.get(login_URLS)
    time.sleep(2)
    driver.find_element_by_id("loginId").send_keys(username)
    driver.find_element_by_id("password").send_keys(password)
    driver.find_element_by_xpath("//button[@type='submit']").click()
    time.sleep(1) 
def create_title():
    d = {}
    for title in laptop_titles:
        d[title] = []
    return d
def auction_time_name(soup):
    return soup.find('span', id="auction_time_remaining").getText().strip()

def scriptDetail(soup, d, page):
    names = soup.find_all('script')
    if not names:
        text_not_found.append(page)
        return
    for i in range(-18, -2):
        string = str(names[i])
        if string.find('mixpanel.track') != -1:
            break;
    key = string.split('mixpanel.track')[1]
    elem = key.split(",")
    for ele in elem:
        try:
            ele = ele.strip()
            half = ele.split(":")
            for index in range(len(half)):
                text = half[index].replace('"', "")
                if text in laptop_titles:
                    name = half[1].strip()
                    name = name.replace('"',"")
                    d[text].append(name)
                    break
        except Exception as e:
            print(e)
            print("scriptDetail")
    return d

def reverse(s):
    str = ""
    for i in s:
        str = i + str
    str = str.strip()
    return str

def getTitle(soup):
    try:
        title = soup.find('h1', itemprop="name").getText()
    except:
        try:
            title = soup.find('div', class_="product-name").getText()
        except Exception as e:
            print("error in getTitle")
            print(e)
            return "none"
    if title.find("$") == -1:
        return "noMoney"
    title = title.lower()  
    for b in blacklist:
        if b in title:
            return "none"
    title = title.replace(", free shipping", "")
    title = title.replace(", msrp", "")
    title = title.replace(", est. retail", "")
    title = title.replace(", retail", "")
    title = title.replace("retail", "")
    title = title.replace("units", "")
    title = title.replace("unit", "")
    title = title.replace("pallet", "")
    title = title.replace("box", "")
    title = reverse(title)
  
    state = ""
    city = ""
    map_price = ""
    condition = ""
    unit = ""
    product_title = ""
    comma_counter = 0
    for c in title:
        if comma_counter == 2 and c == '$':
            comma_counter += 1
            continue
        if(c == ','):
            if comma_counter != 2:
                comma_counter+=1
            continue
        if(comma_counter == 0):
            state += c
        elif comma_counter == 1:
            city += c
        elif comma_counter == 2:
            map_price += c
        elif comma_counter == 4:
            condition += c
        elif comma_counter == 5:
            unit += c
        elif comma_counter >= 6:
            product_title += c
    state = reverse(state).strip()
    city = reverse(city).strip()
    map_price = reverse(map_price).strip()
    condition = reverse(condition).strip()
    unit = reverse(unit).strip()
    product_title = reverse(product_title).strip()

    data = [state, city, map_price, condition, unit, product_title]
    for key in laptop_keyWord:
        if key in product_title:
            return data
    return "none"

def createFolder(id):
    path = r"C:/Users/garys/Desktop/WebApps/B_Stock_Auction/"+str(id)+"/"
    if os.path.exists(path):
        shutil.rmtree(path)
    os.mkdir(path)
    return path

def manifest_download(driver):
    button = driver.find_element_by_id("manifest-download-btn-top")
    button.click()

def get_pictures(soup):
    ul = soup.find('ul', {"class":"product-image-thumbs"})
    index = 0
    os.chdir("C:/Users/garys/Desktop/WebApps/B_Stock_Auction/")
    for li in ul.find_all('li'):
        imgLink = li.find('a')
        png_name = str(index)+".jpg"
        r = requests.get(imgLink['href'])
        with open(png_name,"wb") as f:
            f.write(r.content)
        index += 1
    return index
def move_picture(path, pic_len):
    for i in range(pic_len):
        png_name = str(i)+".jpg"
        old_dir = "C:/Users/garys/Desktop/WebApps/B_Stock_Auction/"+png_name
        shutil.move(old_dir, path)

def move_manifest(folder_path, id):
    path = r"C:/Users/garys/Downloads/"
    extension = '\*csv'
    os.chdir(path)
    files = glob.glob(path+extension)
    result = max(files, key=os.path.getctime) 
    name = str(id)+".csv"
    os.rename(result, name)
    shutil.move(path+name, folder_path)

def getDate(soup):
    return soup.find('span', id="auction_end_time").getText()

def start_crawling(page_begin, page_end):
    driver = webdriver.Chrome('C:/Users/garys/Downloads/chromedriver_win32/chromedriver.exe')
    driver_login(driver)
    d = create_title()
    for page in range(page_begin, page_end):
        url = laptop_URLS + str(page)
        try:
            driver.get(url)
            html = driver.page_source
            soup = BeautifulSoup(html)
            auction_time_remain = auction_time_name(soup)
            if  auction_time_remain == "Auction canceled":
                item_cancel.append(page)
                continue
            elif auction_time_name(soup) != "Auction ended":
                item_running.append(page)
                continue
            titleName = getTitle(soup) #[state, city, map_price, condition, unit, product_title]
            if titleName == "none":
                item_cancel.append(page)
                continue
            elif titleName == "noMoney":
                item_without_money.append(page)
                continue
            folder_path = createFolder(page)
            manifest_found = True
            try:
                manifest_download(driver)
            except Exception as e:
                manifest_found = False
                print("No manifest Download available")
            pics_len = get_pictures(soup)
            move_picture(folder_path, pics_len)
            if manifest_found: 
                move_manifest(folder_path, page)
            time.sleep(1)
            WH = ""
            found = False
            global states
            for state in states:
                if found == True:
                    break
                for i in range(len(states[state])):
                    if states[state][i] == titleName[0]:
                        WH = state
                        found = True
                        break
            d["warehouse"].append(WH)
            scriptDetail(soup, d, page) 
            d["id"].append(page)  
            d["title"].append(titleName[5])
            d["date"].append(getDate(soup))
            d["city"].append(titleName[1])
            d["map_price"].append(titleName[2])
            d["units"].append(titleName[4])
            d["percentage"].append(str(round(float(d["current_price"][-1])/float(d["map_price"][-1]),4)))
        except Exception as e:
            print(url)
    driver.close()
    return d

def run_file(page_start, page_end):
    
    os.chdir("C:/Users/garys/Desktop/WebApps/B_Stock_Auction/")
    data = start_crawling(page_start, page_end)
    df = pd.DataFrame(data)
    file_csv = str(page_start) + "_" + str(page_end) +".csv"
    os.chdir("C:/Users/garys/Desktop/WebApps/B_Stock_Auction/")
    df.to_csv(file_csv, index = False, encoding='utf-8')

run_file(page_begin, page_end)
print("Running items: ", item_running)
print("No $ items: ", item_without_money)
print("Cancel items: ", item_cancel)
print("page broke: ", text_not_found)