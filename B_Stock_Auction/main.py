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

'''
bestbuy = 0
lowes = 1
almo = 2
costco = 3
'''
# URLS_INDEX 

bestbuy_page_begin = 0
bestbuy_page_end = 0

lowes_page_begin = 0
lowes_page_end = 0

almo_page_begin = 4517+1
almo_page_end = 4520

costco_page_begin = 0
costco_page_end = 0

item_running = []
item_without_money = []
item_cancel = []
text_not_found = []

def manifest_download(driver):
    button = driver.find_element_by_id("manifest-download-btn-top")
    button.click()
def move_manifest(folder_path, market, id):
    path = r"C:/Users/garys/Downloads/"
    extension = '\*csv'
    os.chdir(path)
    files = glob.glob(path+extension)
    result = max(files, key=os.path.getctime) 
    name = market+"_"+str(id)+".csv"
    os.rename(result, name)
    shutil.move(path+name, folder_path)

def driver_login(driver):
    conf = yaml.load(open('C:/Users/garys/Desktop/WebApps/B_Stock_Auction/login.yml'))
    username = conf['user']['email']
    password = conf['user']['password']
    driver.get(login_URLS[URLS_INDEX])
    time.sleep(2)
    driver.find_element_by_id("loginId").send_keys(username)
    driver.find_element_by_id("password").send_keys(password)
    driver.find_element_by_xpath("//button[@type='submit']").click()
    time.sleep(1)

def getDate(soup):
    return soup.find('span', id="auction_end_time").getText()

def createFolder(id, market):
    path = r"C:/Users/garys/Desktop/WebApps/B_Stock_Auction/"+market+"_"+str(id)+"/"
    if os.path.exists(path):
        shutil.rmtree(path)
    os.mkdir(path)
    return path

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
    if  URLS_INDEX == 3:
        open = "("
        close = ")"
        start = title.index(open)
        end = title.index(close)
        global manifest
        manifest = title[start+1:end]
    state = ""
    city = ""
    price = ""
    condition = ""
    units = ""
    type = ""
    title = title.replace(", MSRP", "")
    title = title.replace(", Ext. Retail", "")
    title = title.replace(", Retail", "")
    title = title.replace("Retail", "")
    newtitle = ""
    invalid = False
    
    for idx, val in enumerate(title):
        if val == '(':
            invalid = True
        elif val == ')':
            invalid = False
        if not invalid:
            newtitle += val
    newtitle = newtitle.replace(")", "")
    if newtitle.find("Units"):
        newtitle = newtitle.replace("Units", "").strip()
    elif newtitle.find("Unit"):
        newtitle = newtitle.replace("Unit", "").strip()
    elif newtitle.find("Sets"):
        newtitle = newtitle.replace("Sets", "").strip()
    elif newtitle.find("Set"):
        newtitle = newtitle.replace("Set", "").strip()
    newtitle = newtitle.replace(", ,", ",")
    newtitle = newtitle.replace("  ", " ")
    newtitle = newtitle.replace(" ","") 
    comma_counter = 0
    for c in reversed(newtitle):
        if comma_counter == 2 and c == '$':
            comma_counter += 1
            continue
        if c == ',':
            if comma_counter == 0 and len(state) != 0:
                comma_counter += 1
            elif comma_counter == 1 and len(city) != 0:
                comma_counter += 1
            elif comma_counter == 3 and len(condition) != 0:
                comma_counter += 1
            elif comma_counter == 4 and len(units) != 0:
                comma_counter += 1
        if comma_counter == 0:
            state += c
        elif comma_counter == 1:
            city += c
        elif comma_counter == 2:
            price += c
        elif comma_counter == 3:
            condition += c
        elif comma_counter == 4:
            units += c
        elif comma_counter == 5:
            type += c
    state = reverse(state)
    city = reverse(city)
    price = reverse(price)
    condition = reverse(condition)
    units = reverse(units)
    type = reverse(type)
    if URLS_INDEX == 2:
        temp = units
        units = condition
        condition = temp
    condition = condition.replace(",","")
    city = city.replace(",","")
    price = price.replace(",","")
    units = units.replace(",","")
    machine = type.replace(",","")
    kitchen = ['Refrigerators','Microwave','Dishwashers', 'Range','Cooktop','Freezers']
    laundry = ['Washers', 'Dryers', 'Pedestals', 'Stackable', 'Styler','WashTower']
    iskitchen = False
    isLaundry = False
    for k in kitchen:
        if machine.find(k) != -1:
            iskitchen = True
            break
    for l in laundry:
        if machine.find(l) != -1:
            isLaundry = True
            break
    if not iskitchen and not isLaundry:
        return "none"
    if isLaundry and iskitchen:
        return [state, city, price, units, "Mixed"]
    if isLaundry:
        return [state, city, price, units, "Laundry"]
    return [state, city, price, units, "Kitchen"]

def create_column_title(column_titles):
    d = {}
    for title in column_titles:
        d[title] = []
    return d
    
def auction_time_name(soup):
    return soup.find('span', id="auction_time_remaining").getText().strip()

def scriptDetail(soup, d, page, costco=False):
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
                if text in titles:
                    name = half[1].strip()
                    name = name.replace('"',"")
                    d[text].append(name)
                    break
        except Exception as e:
            print(e)
            print("scriptDetail")
    return d
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

def start_crawling(page_begin, page_end, market):
    d = create_column_title(titles)
    
    driver = webdriver.Chrome('C:/Users/garys/Downloads/chromedriver_win32/chromedriver.exe')
    driver_login(driver)
    decrpytion_failed_list = []
    
    # for page in decrpytion_failed_list:
    for page in range(page_begin, page_end):
        url = inventory_URLS[URLS_INDEX]+ str(page)
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
            titleName = getTitle(soup)
            if titleName == "none":
                item_cancel.append(page)
                continue
            elif titleName == "noMoney":
                item_without_money.append(page)
                continue
            folder_path = createFolder(page, market)
            manifest_found = True
            if URLS_INDEX != 3:
                try:
                    manifest_download(driver)
                except Exception as e:
                    manifest_found = False
                    print("No manifest Download available")
            else:
                file_path = file_path_1 + manifest + file_path_2    
            if URLS_INDEX == 3:
                driver.get(file_path)
            pics_len = get_pictures(soup)
            time.sleep(2)
            move_picture(folder_path, pics_len)
            if manifest_found: 
                move_manifest(folder_path, market, page)
            found = False
            WH = ""
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
            d["id"].append(page)  
            d["date"].append(getDate(soup))
            scriptDetail(soup, d, page)
            d["type"].append(titleName[-1])  
            d["city"].append(titleName[1])
            d["value_price"].append(titleName[2])
            d["units"].append(titleName[3])
            d["percentage"].append(str(round(float(d["current_price"][-1])/float(d["value_price"][-1]),4)))
        except Exception as e:
            print(str(page) + " start_crawling")
            print(e)
    d["market"] = market
    return d

def run_file(page_start, page_end):
    os.chdir("C:/Users/garys/Desktop/WebApps/B_Stock_Auction/")
    if URLS_INDEX == 0:
        market = "BESTBUY"
    elif URLS_INDEX == 1:
        market = "LOWES"
    elif URLS_INDEX == 2:
        market = "ALMO"
    elif URLS_INDEX == 3:
        market = "COSTCO"
    data = start_crawling(page_start, page_end, market)
    df = pd.DataFrame(data)
    file_csv = market + "_" + str(page_start) + "_" + str(page_end) +".csv"
    os.chdir("C:/Users/garys/Desktop/WebApps/B_Stock_Auction/")
    df.to_csv(file_csv, index = False)

URLS_INDEX = 0
item_running = []
item_without_money = []
item_cancel = []
text_not_found = []
run_file(bestbuy_page_begin, bestbuy_page_end)
print("Running items: ", item_running)
print("No $ items: ", item_without_money)
print("Cancel items: ", item_cancel)
print("page broke: ", text_not_found)

item_running = []
item_without_money = []
item_cancel = []
text_not_found = []
URLS_INDEX = 1
run_file(lowes_page_begin, lowes_page_end)
print("Running items: ", item_running)
print("No $ items: ", item_without_money)
print("Cancel items: ", item_cancel)
print("page broke: ", text_not_found)

item_running = []
item_without_money = []
item_cancel = []
text_not_found = []
URLS_INDEX = 2
run_file(almo_page_begin, almo_page_end)
print("Running items: ", item_running)
print("No $ items: ", item_without_money)
print("Cancel items: ", item_cancel)
print("page broke: ", text_not_found)

item_running = []
item_without_money = []
item_cancel = []
text_not_found = []
URLS_INDEX = 3
run_file(costco_page_begin, costco_page_end)
print("Running items: ", item_running)
print("No $ items: ", item_without_money)
print("Cancel items: ", item_cancel)
print("page broke: ", text_not_found)