//job("Build and run tests") {
//    container(displayName = "Run mvn install", image = "maven:3.8.7-openjdk-18-slim") {
//        shellScript {
//            content = """
//	            mvn clean install
//            """
//        }
//    }
//}

job("Build, run tests, publish") {
    container(displayName = "Run publish script", image = "maven:3.8.7-openjdk-18-slim") {
        // url of a Space Packages repository
//        env["REPOSITORY_URL"] = "https://maven.pkg.jetbrains.space/bamboit/p/key/my-maven-repo"
        env["REPOSITORY_URL"] = "https://maven.pkg.jetbrains.space/bamboit/p/chartistjsf/maven"

        shellScript {
            content = """
                echo Build and publish artifacts...
                set -e -x -u
                mvn versions:set -DnewVersion=4.0.${'$'}JB_SPACE_EXECUTION_NUMBER
                mvn clean deploy -s settings.xml \
                    -DrepositoryUrl=${'$'}REPOSITORY_URL \
                    -DspaceUsername=${'$'}JB_SPACE_CLIENT_ID \
                    -DspacePassword=${'$'}JB_SPACE_CLIENT_SECRET
            """
        }
    }
}