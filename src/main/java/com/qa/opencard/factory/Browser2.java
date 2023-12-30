package com.qa.opencard.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public enum Browser2 {
    CHROME {
        @Override
        public WebDriver initDriver(OptionsManager optionsManager) {
            return new ChromeDriver(optionsManager.getChromeOptions());
        }
    },
    FIREFOX {
        @Override
        public WebDriver initDriver(OptionsManager optionsManager) {
            return new FirefoxDriver(optionsManager.getFirefoxOptions());
        }
    },
    EDGE {
        @Override
        public WebDriver initDriver(OptionsManager optionsManager) {
            return new EdgeDriver(optionsManager.getEdgeOptions());
        }
    },
    SAFARI {
        @Override
        public WebDriver initDriver(OptionsManager optionsManager) {
            return new SafariDriver();
        }
    };

    public abstract WebDriver initDriver(OptionsManager optionsManager);
}
