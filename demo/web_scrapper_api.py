import _json
import sys
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options
import time


def the_request(req):

    chrome_options = Options()
    chrome_options.add_argument("--headless")
    driver = webdriver.Chrome(options=chrome_options)
    
    driver.get("https://www.pnp.co.za/")

    time.sleep(10)

    element = driver.find_element(By.NAME, "policiesCloseButton").click()

    time.sleep(10)
    # Get element with tag name 'div'
    element = driver.find_element(By.ID, "js-site-search-input").send_keys(req+Keys.ENTER)
    # driver.find_element(By.CLASS_NAME, "btn btn-link").click()
    time.sleep(10)

        # Get all the elements available with tag name 'p'
    items = driver.find_elements(By.CLASS_NAME, 'item-name')
    time.sleep(10)

    price = driver.find_elements(By.CLASS_NAME, 'item-price')
    time.sleep(10)

    for e in zip(items,price):
        print(e[0].text+" "+e[1].text)


def the_response(from_proc):

    pass


def the_process(from_req):

    pass


if __name__ == '__main__':
    the_request("cake")

