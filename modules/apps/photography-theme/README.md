# Internationalizing Your Theme

Beyond basic internationalization which comes out of the box with Liferay, if you would like to add language keys that will be translated, follow the steps below.

## Language Key Translation Automation

1. Add the key in the `Languages.properties` (theme_dir/src/WEB_INF/src/content/Langauges.properties)

2. In one of your .ftl files where you want the key to show up add it like this: `<@liferay.language key="your-key" />`

3. Finally, open up a cmd prompt and navigate to the root directory of this theme and run `gulp deploy -t`.

These steps are demonstrated below:

![demo](https://cloud.githubusercontent.com/assets/20076677/26797195/e7e35738-49e0-11e7-9646-e63f94b75d56.gif)


# Adjusting Theme Settings

Theme settings can be adjusted from the look and feel page by clicking on the **top left icon** and then on the **configure** section of the public pages as show below:

![demo](https://thumbs.gfycat.com/MeaslyPointedArcticwolf-size_restricted.gif)


# Configuring Your Mail Server To Work With The Contact Us / Web Form Portlet

![Visit this thread as an example of setting up your mail server through the control panel interface](https://web.liferay.com/community/forums/-/message_boards/message/39307247)