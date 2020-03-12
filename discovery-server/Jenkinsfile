node {
    
        def mvnHome
        def dockerHome
        stage('Checkout') {
            git url: 'file://E:/pixogram/discovery-server',  branch: 'master'
            mvnHome = tool 'Local MAVEN'
            dockerHome = tool 'Local DOCKER'
        }

        stage('Build') {
            if (isUnix()) {
                sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
            } else {
                bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
            }
        }

        stage ('Final') {
            build job: 'Api-Gateway-Pipeline', wait: false
        }     

}