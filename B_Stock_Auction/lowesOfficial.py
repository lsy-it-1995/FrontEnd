from turtle import title
from selenium import webdriver
import time
import os
from bs4 import BeautifulSoup
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException
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
driver = webdriver.Chrome()

def find_substring_title(description):
    titleTag = description.find('a')
    contentHTML = str(titleTag)
    key = "aria-label"
    aria_label_starting_index = contentHTML.find(key)
    aria_label_ending_index = contentHTML.find('\"', aria_label_starting_index+len(key)+3)
    title = contentHTML[aria_label_starting_index+len(key)+2: aria_label_ending_index+1]
    return title

def find_model(description):
    inner_div = description.find('div', {"id":"model-id-nmbr"})
    model = inner_div.find("span", {"class": "tooltip-custom"}).text
    return model

def find_price(prices):
    print(prices)
    now_price_div = prices.find("div", {"class":"prdt-actl-pr"})
    # print(now_price_div)
    # now_prices = now_price_div.find_all("span")
    # for now_price in now_prices:
    #     n = now_price.text  
    #     if n != '':
    #         print(n)
    print("--------")
    original_price = prices.find("span", {"class":"was-price"}).text
    print(original_price)
    pass

scroll = 5

for idx, val in enumerate(links):
    driver.get(val)
    time.sleep(1)
    driver.maximize_window()
    time.sleep(5)
    body = driver.find_element(By.CSS_SELECTOR, 'body')
    body.send_keys(Keys.PAGE_DOWN)
    while scroll > 0:
        body.send_keys(Keys.PAGE_DOWN)
        scroll -= 1
        time.sleep(1)
    
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
        for price in prices:
            price = find_price(prices)
            break

        break

    driver.implicitly_wait(3) # seconds
