# Kitchen Store – Android app

The app opens the shared Kitchen Store page (https://claude.ai/artifact/9ksegt2KirTN9Lycr26uJP)
in Chrome, so the phone and the web version always show the same stock.

- Each staff member signs in to Claude once in Chrome on the phone.
- The owner shares the page with each staff member as **Contributor** (Share button on the page).

## Getting the APK
Every push to `main` builds the app on GitHub Actions and publishes **KitchenStore.apk** under
**Releases → "Kitchen Store app (latest)"**. Download it on the phone and install it
(allow "Install unknown apps" for your browser when Android asks).

## Changing the link
Edit `store_url` in `app/src/main/res/values/strings.xml`, raise `versionCode` in `app/build.gradle`, and push.
