# imports
import time

from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait


class SuperDriver:

    def __init__(self, driver: object) -> object:
        self._driver = driver
        self.WAIT_TIMEOUT = 20

    ###
    ### browsing methods
    ###

    def switch_tab(self):
        actions = ActionChains(self._driver)
        actions.key_down(Keys.CONTROL).key_down(Keys.TAB).key_up(Keys.TAB).key_up(Keys.CONTROL).perform()


    ###
    ### window methods
    ###

    '''
    def switch_and_maximize(self):
        time.sleep(3)
        #self._driver.close()
        self._driver.switch_to.window(self._driver.window_handles[0])
        self._driver.maximize_window() 
    '''

    def maximize_window(self):
        self._driver.maximize_window()

    def switch_to_last_window_and_maximize(self):
        time.sleep(3)
        nwindows = len(self._driver.window_handles)
        self._driver.switch_to.window(self._driver.window_handles[nwindows-1])
        self._driver.maximize_window()

    def switch_to_main_window_and_maximize(self):
        time.sleep(3)
        self._driver.switch_to.window(self._driver.window_handles[0])
        self._driver.maximize_window()


    ###
    ### elements methods
    ###

    def wait_and_get(self, mode, key):
        try:
            return WebDriverWait(self._driver, self.WAIT_TIMEOUT).until(EC.visibility_of_element_located((mode, key)))
        except NoSuchElementException as e:
            return False


    def wait_and_get_by_id(self, key):
        return self.wait_and_get(By.ID, key)

    def wait_and_get_by_tag_name(self, key):
        return self.wait_and_get(By.TAG_NAME, key)

    def wait_and_get_by_xpath(self, key):
        return self.wait_and_get(By.XPATH, key)

    ##

    def wait_and_click(self, mode, id):
        elem = self.wait_and_get(mode, id)
        elem.click()

    def wait_and_click_by_xpath(self, id):
        self.wait_and_click(By.XPATH, id)

    def wait_and_click_by_link_text(self, id):
        self.wait_and_click(By.LINK_TEXT, id)

    def wait_and_click_by_css_selector(self, id):
        self.wait_and_click(By.CSS_SELECTOR, id)


    def wait_and_follow_href(self, mode, id):
        elem = self.wait_and_get(mode, id)
        self._driver.get(elem.get_attribute("href"))

    ###
    ### assert methods
    ###

    ###
    ### Mouseover methods
    ###
    def move_between_x_axis(self, obj1, obj2):
        # get intermediate point
        x = obj2.location["x"]
        y = obj1.location["y"]
        # move
        ActionChains(self._driver).move_by_offset(x, y).move_to_element(obj2).perform()

    def move_between_y_axis(self, obj1, obj2):
        # get intermediate point
        x = obj1.location["x"]
        y = obj2.location["y"]
        # move
        ActionChains(self._driver).move_by_offset(x, y).move_to_element(obj2).perform()

    def wait_and_move_between_x_axis(self, mode, id1, id2):
        obj1 = self.wait_and_get(mode, id1)
        obj2 = self.wait_and_get(mode, id2)
        self.move_between_x_axis(obj1, obj2)

    def wait_and_move_between_y_axis(self, mode, id1, id2):
        obj1 = self.wait_and_get(mode, id1)
        obj2 = self.wait_and_get(mode, id2)
        self.move_between_y_axis(obj1, obj2)

    def wait_and_move_to(self, mode, id):
        elem = self.wait_and_get(mode, id)
        ActionChains(self._driver).move_to_element(elem).perform()
'''
    def move_between_x_axis(self, obj1, obj2):
        # get intermediate point
        x = obj2.location["x"]
        y = obj1.location["y"]
        # move
        ActionChains(self._driver).move_by_offset(x, y).move_to_element(obj2).perform()

    def move_between_y_axis(self, obj1, obj2):
        # get intermediate point
        x = obj1.location["x"]
        y = obj2.location["y"]
        # move
        ActionChains(self._driver).move_by_offset(x, y).move_to_element(obj2).perform()

    def wait_and_move_between_x_axis(self, mode, id1, id2):
        obj1 = self.wait_and_get(self._driver, mode, id1)
        obj2 = self.wait_and_get(self._driver, mode, id2)
        self.move_between_x_axis(self._driver, obj1, obj2)

    def wait_and_move_between_y_axis(self, mode, id1, id2):
        obj1 = self.wait_and_get(self._driver, mode, id1)
        obj2 = self.wait_and_get(self._driver, mode, id2)
        self.move_between_y_axis(self._driver, obj1, obj2)

    def wait_and_move_to(self, mode, id):
        elem = self.wait_and_get(self._driver, mode, id)
        ActionChains(self._driver).move_to_element(elem).perform()
'''
    ###
    ###
    ###

    ###
    ###
    ###
