pipeline {
    agent any

    stages {
        stage('Code pull') {
            when {
                expression {
                    String s = "NOT MERGED"
                    if(params.merged) {
                        s = "MERGED"
                    }
                    echo "PR CURRENT STATUS: ${params.current_status}"
                    echo "PR MERGED STATUS: $s"
                    return params.current_status == "closed" && params.merged == true
                }
            }
            steps {
                git 'https://github.com/Sheikh92857/pipeline_test'
            }
        }
        stage('Build') {
            when {
                expression { return params.current_status == "closed" && params.merged == true }
            }
            steps {
                sh '''
            #!/bin/sh
            flutter build apk --debug
            '''
            }
        }
        // stage('Test') {
        //     when {
        //         expression { return params.current_status == "closed" && params.merged == true }
        //     }
        //     steps {
        //         sh 'flutter test'
        //     }
        // }
        // stage('Publish') {
        //     when {
        //         expression { return params.current_status == "closed" && params.merged == true }
        //     }
        //     steps {
        //         appCenter apiToken: 'b5bf5ef5307d32c0ea47da2257d2634443b66f4a',
        //                 ownerName: 'hassan57928@gmail.com',
        //                 appName: 'pipeline_test',
        //                 pathToApp: 'build/app/outputs/apk/debug/app-debug.apk',
        //                 distributionGroups: 'AlphaTester'
        //     }
        // }
    }
}
