pipeline {

  agent any
environment {

    PATH = "C:\\WINDOWS\\SYSTEM32"

}
    stages {
        stage('build'){
            steps{
                dir('app'){
       bat label: '', script: 'flutter build apk --release'
                }
        }}


        // stage('DISTRIBUTE') {
        //     steps {
        // appCenter apiToken: "APKKEY", 
        // appName: 'sampleApp',
        // distributionGroups: 'Test', 
        // notifyTesters: false, 
        // ownerName: 'sample', 
        // pathToApp: 'app\\build\\app\\outputs\\apk\\release\\app-release.apk', 
        // releaseNotes: '$BUILD_NUMBER'
        //     }
        // }
    }
}