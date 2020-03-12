node {
 
        def dockerHome
        stage('Checkout') {
            git url: 'file://E:/pixogram',  branch: 'master'
            
            dockerHome = tool 'Local DOCKER'
        }

        stage ('Run') {
            // docker.image("cts/discovery-server:0.0.1-SNAPSHOT").run('-p 8761:8761  --name discovery')
            bat(/"${dockerHome}\Docker\resources\bin\docker-compose" up/)
         }  

}