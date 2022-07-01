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
links = ["https://www.lowes.com/search?searchTerm=lg&catalog=4294857973"]
# links = ["https://www.lowes.com/search?searchTerm=lg&catalog=4294857981",
#         "https://www.lowes.com/search?searchTerm=lg&catalog=4294857973",
#         "https://www.lowes.com/search?searchTerm=lg&catalog=4294857925"]
        
chromdriverPath = 'C:/Users/lsy/Downloads/chromedriver_win32/chromedriver.exe'
# edgePath = 'C:/Users/lsy/Downloads/edgedriver_win32/msedgedriver.exe'
options = webdriver.ChromeOptions()
options.add_argument("--no-sandbox")

s=Service(chromdriverPath)
driver = webdriver.Chrome(service=s, options = options)
title_list = []
model_list = []
discounted_price_list = []
original_price_list = []

scroll = 5
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
    inner_div = description.find('div', {"id":"model-id-nmbr"})
    model = inner_div.find("span", {"class": "tooltip-custom"}).text
    return model

def find_price(prices):
    container = prices.find("div",{"class": "mlt-price"})
    container = container.find("div", {"class": "slpl-price-info"})
    now_price_div = container.find("div", {"data-selector":"splp-prd-act-$"})
    print(now_price_div)
    contentHTML = str(now_price_div)

    key = "<span class>"  
    price_starting_index = contentHTML.find(key)+len(key)
    price_ending_index = contentHTML.find("</span>", price_starting_index)
    discount_price = contentHTML[price_starting_index:price_ending_index]
    print(discount_price)

    print("--------")
    container = prices.find("div",{"class": "mlt-price"})
    container = container.find("div",{"class": "prdt-prom"})
    original_price = container.find_all("span", {"class":"was-price"}).text
    print(original_price)
    return discount_price, original_price

def loading_stuff(driver):
    if no_next == 1:
        return
    html = driver.page_source
    soup = BeautifulSoup(html, "html.parser")
    divs = soup.find_all('div', {"class":"tile_group"})
    print(len(divs))
    
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
    try:
        element = WebDriverWait(driver, 10).until(EC.element_to_be_clickable((By.CSS_SELECTOR, "ul.splp-pag-lst a[aria-label='arrow-right']")))
        element.click()
        loading_stuff(driver)
    except Exception:
        no_next = 1
        print("no next")
        

for idx, val in enumerate(links):
    driver.get(val)
    driver.maximize_window()
    time.sleep(3)
    body = driver.find_element(By.CSS_SELECTOR, 'body')
    body.send_keys(Keys.PAGE_DOWN)
    while scroll > 0:
        body.send_keys(Keys.PAGE_DOWN)
        scroll -= 1
        time.sleep(1)
    no_next = 0
    loading_stuff(driver)
    driver.implicitly_wait(3) # seconds


print(model_list)
print(title_list)
print(original_price_list)
print(discounted_price_list)
