from argparse import Action
from logging import exception
from bs4 import BeautifulSoup
from selenium.webdriver.common.by import By
from selenium import webdriver
import time
from selenium.webdriver.common.by import By
import re
import pandas as pd
modelMap = {}
with open('ge_appliances.txt') as f:
    for line in f:
        line = re.split(r'\t+', line)
        line[1] = line[1].replace("\\", "")
        line[1] = line[1].replace("\n", "")
        modelMap[line[0]] = line[1]

url = "https://www.geappliances.com/shop?"

driver = webdriver.Chrome('C:/Users/garys/Downloads/chromedriver_win32/chromedriver.exe')
list_not_findable = []
modelPriceMap = {}
def searching_list():
    driver.get(url)
    driver.maximize_window()
    html = driver.page_source
    soup = BeautifulSoup(html)
    time.sleep(1)
    loc = driver.find_element(By.CSS_SELECTOR, "button[class='onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon']").click()
    time.sleep(1)
    driver.find_element_by_id("postal_code").send_keys("91764")
    time.sleep(1)
    driver.find_element_by_xpath('//button[text()="Continue"]').click()
    d = {}
    d["model"] = []
    d["price"] = []
    for k, v in modelMap.items():
        driver.find_element_by_id("search_input_mobile").send_keys(k)
        driver.find_element_by_xpath("//button[@type='submit']").click()
        time.sleep(5)
        try:
            num = driver.find_element_by_xpath("//span[@class='searchCount ng-binding']").text
            if num != "1":
                list_not_findable.append(k)
                continue
        except Exception as e:
            print(k)
            list_not_findable.append(k)
        try:    
            price = driver.find_element_by_xpath("//span[@class='price price--withoutTax ng-binding']").text
            d["model"].append(k)
            d["price"].append(price)
        except Exception as e:
            print(k)
            list_not_findable.append(k)
    driver.close()
    return d

data = searching_list()

df = pd.DataFrame(data)
print(df)
file_csv = "GE_MAP_Price" +".csv"
df.to_csv(file_csv, index = False)

print(list_not_findable)