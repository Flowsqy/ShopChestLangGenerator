# LangGenerator

Little application used to configure [ShopChest](https://github.com/Flowsqy/ShopChest)

## How to use

You need a working version of java.

### Quick video instructions

You can watch this [video](https://youtu.be/DYMR4fMmgnM)

### Text instructions

Or you can follow this guide.
Yeah, I know there is a lot of text, but I guarantee you that it's easy (and can be done in less than one minute)

#### Create a working directory

Just create a directory somewhere

#### Get the minecraft language file

To get a Minecraft language file (that is not `en_us`), follow these steps:

1. Get in your `.minecraft/assets/indexes` folder and open the file that is named the desired version.
2. Search for your language code (e.g. `de_de` or `fr_fr`). There should be 2 results: One for the realms language file
   and one for the normal language file.
3. Copy the `hash` code and search for it in the `.minecraft/assets` folder. The hash code is the filename of the
   language file.

Once done, copy the language file into your working directory

#### Building the application

This can be done once. Doing it each time ensure you to have the latest version of the application.

1. Clone the repository to your computer.
2. Go inside the project folder
   (where ShopChestLangGenerator project files are located)
3. Open a command prompt. Then type `gradlew assembleDist` on Windows or `./gradlew assembleDist` on Linux and macOS
4. Go to the `app/build/distributions` folder
5. Decompress the archive of your choice, either the tar or the zip one, it does not matter and move the `app` folder
   into your working directory

#### Generate the output file

1. Move your language input file into the `app/bin` folder
2. Go to the `app/bin` folder and open a command prompt
3. Type `app.bat <LanguageInputFile>` on Windows or `./app <LanguageInputFile>` on Linux and macOS.
   Don't forget to replace `<LanguageInputFile>` by the actual name of your language input file.

You're done ! It should have generated the desired language file.
<br>
If not, it should have generated an error message in your command prompt.
Then, follow the error instructions or open an issue on GitHub.

