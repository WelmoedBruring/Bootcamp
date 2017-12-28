using System;
using FluentAssertions;
using Microsoft.VisualStudio.TestTools.UnitTesting;
//using NFluent;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace Bootcamp
{
    [TestClass]
    public class FirstCSSeleniumTest
    {
        [TestMethod]
        public void LogInSuccesful()
        {
            IWebDriver driver = new ChromeDriver();

            // Open website
            driver.Navigate().GoToUrl("https://techblog.polteq.com/testshop/index.php");

            // Find and click the login button
            driver.FindElement(By.ClassName("login")).Click();

            // Enter email address
            driver.FindElement(By.Id("email")).SendKeys("welmoed.bruring@polteq.com");

            // Enter password
            driver.FindElement(By.Id("passwd")).SendKeys("testw8woord");

            // Click login button
            driver.FindElement(By.Id("SubmitLogin")).Click();
            
            // Check that the My Account page is displayed
            String actualPageHeading = driver.FindElement(By.ClassName("page-heading")).Text;
            String expectedPageHeading = "MY ACCOUNT";
            //Check.That(actualPageHeading).Equals(expectedPageHeading);
            actualPageHeading.Should().Be(expectedPageHeading, "the My Account page should be displayed");

            //Assert.AreEqual(actualPageHeading, expectedPageHeading);

            // Terminate the browser
            driver.Quit();

        }
    }
}
