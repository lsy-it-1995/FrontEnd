from turtle import title
from selenium import webdriver
import time
import os
from bs4 import BeautifulSoup
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import re
import csv
from itertools import zip_longest
links = ["https://www.lowes.com/search?searchTerm=lg&catalog=4294857981"]
# links = ["https://www.lowes.com/search?searchTerm=lg&catalog=4294857981",
#         "https://www.lowes.com/search?searchTerm=lg&catalog=4294857973",
#         "https://www.lowes.com/search?searchTerm=lg&catalog=4294857925"]
        
chromdriverPath = 'C:/Users/lsy/Downloads/chromedriver_win32/chromedriver.exe'
# edgePath = 'C:/Users/lsy/Downloads/edgedriver_win32/msedgedriver.exe'
options = webdriver.ChromeOptions()
options.add_argument("--no-sandbox")

s=Service(chromdriverPath)

params = {
    "latitude": 34.0661889357707,
    "longitude": -117.61866091403989,
    "accuracy": 100
}
driver = webdriver.Chrome(service=s, options = options)
driver.execute_cdp_cmd("Page.addScriptToEvaluateOnNewDocument", {
    "source":
        "const newProto = navigator.__proto__;"
        "delete newProto.webdriver;"
        "navigator.__proto__ = newProto;"
})
driver.execute_cdp_cmd("Page.setGeolocationOverride", params)



no_next = 0

def find_substring_title(description):
    titleTag = description.find('a')
    contentHTML = str(titleTag)
    key = "aria-label"
    aria_label_starting_index = contentHTML.find(key)+len(key)
    aria_label_ending_index = contentHTML.find('\"', aria_label_starting_index+3)
    title = contentHTML[aria_label_starting_index+2: aria_label_ending_index+1]
    return title

def find_model(description):
    try:
        inner_div = description.find('div', {"id":"model-id-nmbr"})
        model = inner_div.find("span", {"class": "tooltip-custom"}).text
    except:
        return ""
    return model

def find_price(prices):
    discount_price = 0
    original_price = 0
    try:
        try:
            container = prices.find("div",{"class": "mlt-price"})
            print("way 1")
        except:
            try:
                container = container.find("div", {"data-selector": "splp-prd-$"})
                print("way 2")
            except:
                print("no price")
                return 0, 0
        if container == None:
            return 0, 0
        container = container.find("div", {"class": "slpl-price-info"})
        now_price_div = container.find("div", {"data-selector":"splp-prd-act-$"})
        
        contentHTML = str(now_price_div)
        
        key = "<span class=\"\">"  
        price_starting_index = contentHTML.find(key)+len(key)
        price_ending_index = contentHTML.find("</span>", price_starting_index)
        discount_price = contentHTML[price_starting_index:price_ending_index]
        print(discount_price)
    except IOError:
        print(IOError)
    discount_price = discount_price.strip()
    if len(discount_price) == 0:
        return 0, 0
    print("--------")
    try: 
        try: 
            container = prices.find("div",{"data-selector": "splp-prd-$"})
            if container == None:
                return discount_price, discount_price
            container = container.find("p",{"class": "mlt-promo"})
            if container == None:
                return discount_price, discount_price
            print(container)
            try:
                o_price = container.find("span",{"class":"was-price"})
                contentHTML = str(o_price)
                print(contentHTML)
                skip_String = "View Price in Cart"
                key = "$"
                if contentHTML != None and skip_String in contentHTML:
                    return discount_price, discount_price
                closing_tag = "</span>"
                contentHTML = contentHTML[0: contentHTML.index(closing_tag) + len(closing_tag)]
                original_price_start = contentHTML.rindex(key)+len(key)
                original_price_end = contentHTML.find(closing_tag, original_price_start)
                original_price = contentHTML[original_price_start:original_price_end]
            except IOError:
                print(IOError)
        except IOError:
            print("no original price")
            print(IOError)
        print(original_price)
    except IOError:
        print(IOError)
    return discount_price, original_price

def loading_stuff(driver, no_next, scroll, count):
    if no_next == 1:
        return
    body = driver.find_element(By.TAG_NAME, 'body')
    body.send_keys(Keys.PAGE_DOWN)
    while scroll > 0:
        body.send_keys(Keys.PAGE_DOWN)
        scroll -= 1
        time.sleep(1)
    html = driver.page_source
    soup = BeautifulSoup(html, "html.parser")
    divs = soup.find_all('div', {"class":"tile_group"})
    print(len(divs))
    title_list = []
    model_list = []
    discounted_price_list = []
    original_price_list = []
    for div in divs:
        descriptions = div.find_all('div', {"order":6})
        prices = div.find_all('div', {"order":7})
        for description in descriptions:
            title = find_substring_title(description)
            model = find_model(description)
            title_list.append(title)
            model_list.append(model)
        for price in prices:
            discounted_price, original_price = find_price(price)
            discounted_price_list.append(discounted_price)
            original_price_list.append(original_price)
    d = [title_list, model_list, discounted_price_list, original_price_list]
    export_data = zip_longest(*d, fillvalue = '')
    fileName = 'numbers '+ str(count)+'.csv'
    with open(fileName, 'w', encoding="ISO-8859-1", newline='') as myfile:
      wr = csv.writer(myfile)
      wr.writerow(("Title", "model","discounted Price", "original price"))
      wr.writerows(export_data)
    myfile.close()
    count += 1

    print("FAST")
    time.sleep(5)
    loading_stuff(driver, 0, 5, count)

    # try:
    #     next_button = soup.find('a',{"aria-label":"arrow-right"})
    #     print(next_button)
    #     next_button.find_element(By.XPATH, "//svg[@data-test='arrow-right']").click()
    # except Exception:
    #     no_next = 1
    #     print("no next")
        

for idx, val in enumerate(links):
    driver.get(val)
    driver.maximize_window()
    time.sleep(3)
    no_next = 0
    loading_stuff(driver, no_next, 5, 1)
    driver.implicitly_wait(3) # seconds


# print(model_list)
# print(title_list)
# print(original_price_list)
# print(discounted_price_list)
