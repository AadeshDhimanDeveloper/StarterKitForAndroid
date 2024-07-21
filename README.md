## Android Starter Kit

Welcome to the Android Starter Kit! This project is designed to help you quickly start developing
your own Android application. By cloning this repository, you can immediately begin implementing
your project's unique features. Here’s what this starter kit provides:

### Features
* Project Structure: Built using the MVVM pattern, dagger hilt(for dependencies injection), Retrofit, Glide, and SharedPreferences.
* Strict Mode Policy: Detects issues like disk reads/writes, content URIs with improper permissions, memory leaks, SQLite leaks, and more, helping you build a smooth and efficient application.
* Code Quality Tools: Includes Detekt, Lint, and Ktlint for maintaining high code quality. When you try to commit your code all these tools will check your code, and if any issue is found in your project the commit will fail with proper error.

### Best Practices

While the lint and static analysis tools included in this project will help you improve code
quality and prevent potential errors, they cannot catch everything.
For instance, they might not detect duplicate code or certain design flaws.
To write more robust and testable code, here are some additional guidelines:

* Split methods with more than two return statements into smaller methods.
* If a code is written twice make a function for that so that, that code can be reusable, and if in the future you have to modify that logic then you only need to modify it in one place. It will save you time and avoid bug scenarios.
* Use meaningful and consistent names for variables, methods, and classes to improve code readability e.g. if a function is for updating the title the function name should be `updateTitle`.
* Each method should have a single responsibility, which means if a method is for updating the title of the window so do not put any other logic in that method. It will prevent your code from becoming messy and make it more readable and testable.
* Write clear comments to explain complex logic or decisions in your code. It will help you and others to understand your code and what this method is doing.
