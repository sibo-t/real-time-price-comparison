import _json
import sys
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys


def the_request(req):

    driver = webdriver.Chrome()
    driver.get("https://www.pnp.co.za/")

    element = driver.find_element(By.NAME, "policiesCloseButton").click()

    # Get element with tag name 'div'
    element = driver.find_element(By.ID, "js-site-search-input").send_keys(req+Keys.ENTER)
    # driver.find_element(By.CLASS_NAME, "btn btn-link").click()

        # Get all the elements available with tag name 'p'
    items = driver.find_elements(By.CLASS_NAME, 'item-name')
    price = driver.find_elements(By.CLASS_NAME, 'item-price')
    for e in zip(items,price):
        print(e[0].text+" "+e[1].text)


def the_response(from_proc):

    pass


def the_process(from_req):

    pass


if __name__ == '__main__':
    the_request("bread")

