# GenerativeAIGeminiAndroid
This is a text generation capability in a Chatbox on Android that uses the Gemini API, a Generative AI from Google.

Getting started
---------------
You can clone or fork this repo and use it freely. If there are build errors, in Android Studio go to `Tools -> Android -> SDK Manager` and install any available updates. Then go to `Build -> Clean Project` and finally `Build -> Rebuild Project`.

Remember, you will need getting the last API version and updates.

Another thing to keep in mind before proceeding is to replace the empty `apiKey` from `AiGenerativeModel.kt` with your own key you get from your [Google AI Studio account](https://aistudio.google.com/app/u/1/apikey).

Please remember
-----------
It is strongly recommended that you do not check an API key into your version control system. Instead, you should store it in a local.properties file (which is located in your project's root directory, but excluded from version control), and then use the Secrets Gradle plugin for Android to read your API key as a Build Configuration variable.

URLs of interest or documentation
-----------
https://aistudio.google.com//library
https://ai.google.dev/gemini-api/docs/quickstart?lang=android
https://ai.google.dev/gemini-api/docs/quickstart?lang=swift
https://ai.google.dev/gemini-api/docs
https://developers.googleblog.com/en/tune-gemini-pro-in-google-ai-studio-or-with-the-gemini-api/
https://ai.google.dev/gemini-api/docs/model-tuning
https://ai.google.dev/pricing#1_5flash
https://ai.google.dev/gemini-api/docs/models/generative-models 

Contact
----------
For anything about the project, you can email me at eagb.mb@gmail.com.
You can visit my [website](https://eagbcorp.com).