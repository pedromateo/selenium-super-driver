import time

from lib.utils.super_driver.SuperDriver import SuperDriver


class SuperChecker():

    def __init__(self, checker: object, driver: object) -> object:
        self._checker = checker
        self._driver = driver
        self._superDriver = SuperDriver(driver)

    ###
    ### elements
    ###

    def assert_element_present(self, how, what):
        elem = self._superDriver.wait_and_get(how, what)
        self._checker.assertTrue(elem != False)

    def assert_element_present_by_xpath(self, key):
        self.assert_element_present("xpath", key)

    def assert_element_present_by_css_selector(self, key):
        self.assert_element_present("css selector", key)

    def assert_element_present_by_link_text(self, key):
        self.assert_element_present("link text", key)

    def assert_element_present_by_partial_link_text(self, key):
        self.assert_element_present("partial link text", key)

    def assert_element_present_by_id(self, key):
        self.assert_element_present("id", key)


    ###
    ### title
    ###

    def check_title(self, title):
        max = 40;
        attempts = 0;
        while title != self._driver.title and attempts < max:
            time.sleep(0.3)
            attempts += 1

        self._checker.assertEqual(title, self._driver.title)

    def check_oops(self):
        try:
            element = self._superDriver.wait_and_get_by_tag_name('h3')
        except:
            return;

        if element != False:
            self._checker.assertTrue(element.text != '¡ Ooooops !')
            self._checker.assertTrue(element.text != 'Oops!')

    def check_link_and_title(self, title):
        self.check_title(title);
        self.check_oops();

'''
    def is_element_present(self, how, what):

        try:
            self._driver.find_element(by=how, value=what)
        except NoSuchElementException as e:
            raise Exception("Element not found")
            return False
        return True


    def asserTrue_by_xpath(self,key):
        """
              Finds an element by xpath.

              :Args:
               - xpath - The xpath locator of the element to find.

              :Usage:
                  driver.find_element_by_xpath('//div/td[1]')
              """
        self.is_element_present("xpath",key)

    def asserTrue_by_css_selector(self,key): #NO TESTEADO
        """
                Finds an element by css selector.

                :Args:
                 - css_selector: The css selector to use when finding elements.

                :Usage:
                    driver.find_element_by_css_selector('#foo')
                """
        self.is_element_present("css selector",key)

    def asserTrue_by_link_text(self,key):
        """
                Finds an element by link text.

                :Args:
                 - link_text: The text of the element to be found.

                :Usage:
                    driver.find_element_by_link_text('Sign In')
                """
        self.is_element_present("link text",key)

    def asserTrue_by_partial_link_text(self, key):
        """
                Finds an element by a partial match of its link text.

                :Args:
                 - link_text: The text of the element to partially match on.

                :Usage:
                    driver.find_element_by_partial_link_text('Sign')
                """

        self.is_element_present("partial link text", key)

    def asserTrue_by_id(self, key):

        """Finds an element by id.

               :Args:
                - id\_ - The id of the element to be found.

               :Usage:
                   driver.find_element_by_id('foo')
               """
        self.is_element_present("id", key)

    def asserTrue_by_name(self, key):
        """
                Finds elements by name.

                :Args:
                 - name: The name of the elements to find.

                :Usage:
                    driver.find_elements_by_name('foo')
                """
        self.is_element_present("name", key)

    def asserTrue_by_class_name(self, key):
        """
                Finds an element by class name.

                :Args:
                 - name: The class name of the element to find.

                :Usage:
                    driver.find_element_by_class_name('foo')
                """

        self.is_element_present("class name", key)
'''