## AzimoNote

Example Android Project created for sake of presenting one of many possible ways how to integrate [AutomationTestSupervisor](https://github.com/AzimoLabs/AutomationTestSupervisor) tool with Android Application. AutomationTestSupervisor is our open source Python tool for managing and organizing Android automation test session.

-------------

#### TL;DR quick launch

1. Enter directory where you want to fetch project.

    ```cd ~/<directory>```

2. Clone AzimoNote repository:

    ```git clone https://github.com/AzimoLabs/AzimoNote.git```

3. Enter to root directory of AzimoNote project. Make sure submodules are synchronized (files of AutomationTestSupervisor should appear in `./automation/AutomationTestSupervisor/` directory):

    ```git submodule update --init --recursive```

    ```git submodule sync --recursive```

4. Make sure you have `Python 3.6` or newer installed and available from terminal. (If you are MAC OS user we highly recommend [Homebrew](https://brew.sh/) -> `brew install python3`.)

5. Make sure your Android SDK directory is up to date and set in system environment variable `ANDROID_HOME`.

6. Make your SDK includes `Android API 23` and emulator image `system-images;android-23;google_apis;x86_64` - because those two dependencies are used in AzimoNote project configuration. If you want to launch more than 1 AVD for tests you also need `Intel HAXM` installed and configured. (2AVD -> ~3GB RAM, 3AVD -> ~4,5GB RAM etc.)

7. Navigate to AutomationTestSupervisor folder.

    ```cd <directory>/AzimoNote/automation/automationTestSupervisor```

8. Launch tests with one of following commands according to your needs and interest. Example of command assembles (of course there are more combinations and options + you can create your own):

  - function tests on currently connected/visible devices:

    ```python3 Launcher.py -tset function_tests```

  - end-to-end tests on AVD created and launched by AutomationTestSupervisor:

    ```python3 Launcher.py -tset endtoend_tests -aset 2AVD-23```

  - function tests on AVD created and launched by AutomationTestSupervisor with screen recording:

    ```python3 Launcher.py -tset function_tests -aset 1AVD-23 -lplan record_videos```

-------------

### About application structure

#### General

1. It is **100% offline**. Connection with Cloud was mocked via `CloudMock.java` class which distributes responses to app.
2. It is using **RxJava** for simulate internet connection delay and handling asynchronous operation.
3. It is using Dependency Injection framework **Dagger 2** as it's simplifying monitoring of tested app state in UI tests.
4. [ConditionWatcher](https://github.com/AzimoLabs/ConditionWatcher) lib is used for providing synchronisation between UI test thread and tested application.
5. AzimoNote consists of following screens:
  - **DispatcherActivity** - launching screen with decides where user should be redirected depending on it's login status
  - **WelcomeActivity** - screen for welcoming not logged in users, allows to pick between Login and Register
  - **LoginActivity** - screen that allows user to sign in or switch to register process
  - **RegisterActivity** - screen that allows user to sign up or switch to login process
  - **NotesActivity** - screen that displays all user notes in chronological order
  - **AddNoteActivity** - screen that allows to type a note message and send it to Application

#### UI tests

Currently following functions of AzimoNote are UI tested:

a) End-to-end tests (6 tests):
  - user redirection
  - user login
  - user logout
  - user registration
  - adding and displaying notes

b) Function tests (10 tests):
  - user login formula, navigation, error handling
  - user registration formula, navigation, error handling
  - welcome screen navigation

#### AutomationTestSupervisor - integration between two projects
AutomationTestSupervisor is added as submodule of AzimoNote project. It is done via `.submodules` file located in the root of AzimoNote project:

Content of `.submodules` file:

    [submodule "automation/automationTestSupervisor"]
    	path = automation/automationTestSupervisor
    	url = https://github.com/AzimoLabs/AutomationTestSupervisor

When downloading AzimoNote repository, after synchronisation of submodules it's files should be visible in `<project_root>/automation/automationTestSupervisor` directory.

#### AutomationTestSupervisor - configuration
Nothing was changed inside AutomationTestSupervisor project. It expects four configuration files, which are adjusted to tested project, in relative directory to `automationTestSupervisor` folder - `../AutomationTestSupervisorConfig/`. It requires following files:


###### [testManifest.json](https://github.com/AzimoLabs/AzimoNote/blob/master/automation/automationTestSupervisorConfig/testManifest.json)

All test packages from AzimoNote project are listed in this .json file. Those packages are used to create test set configs. Test set name can be added as a parameter to AutomationTestSupervisor launching command. 

Available test sets for AzimoNote project:

- function_tests
- endtoend_tests

###### [avdManifest.json](https://github.com/AzimoLabs/AzimoNote/blob/master/automation/automationTestSupervisorConfig/avdManifest.json)

Stores schemas for AVD devices that can be created, launched and supervised by AutomationTestSupervisor. Schemas are used for creating AVD set configs. AVD set name can be added as a parameter to AutomationTestSupervisor launching command. 

Available AVD sets:
- 3AVD-23
- 2AVD-23
- 1AVD-23

###### [pathManifest.json](https://github.com/AzimoLabs/AzimoNote/blob/master/automation/automationTestSupervisorConfig/pathManifest.json)

Stores paths configured for setup where AutomationTestSupervisor is pulled into AzimoNote project.

- SDK dir read from env variable `ANDROID_HOME`
- AVD created and stored in directory `~/.android/`
- path to tested project root directory set to `../../`
- path to .apk files in tested project set to `../../app/build/outputs/apk/`

###### [launchManifest.json](https://github.com/AzimoLabs/AzimoNote/blob/master/automation/automationTestSupervisorConfig/launchManifest.json)

Stores possible launch plans for AutomationTestSupervisor. Few universal and optional launch configurations were constructed for AzimoNote project:

- default (used if no launch plan was added to launching command)
- record_videos
- no_apk_build
- no_apk_build_record_videos


