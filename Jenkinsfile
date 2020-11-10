def server = Artifactory.server '-1479459735@1465423003960'
def rtGradle = Artifactory.newGradleBuild()
def buildInfo
pipeline {
    agent { node { label 'k8s-jenkins-agent' } }
    environment {
        GRADLE_USER_HOME="${env.WORKSPACE}/.gradle"
        GRADLE_OPTS="-Dorg.gradle.daemon=false"
    }    
    options {
        disableConcurrentBuilds()
    }    
    stages {
        stage('SCM') {
            steps {
                checkout scm
            }
        }
        stage('Pre-build') {
            steps {
                dir (".") {
                    sh 'pwd'
                    sh 'ls -la'
                    sh 'chmod 755 ./gradlew'
                }
            }            
        }
        stage('Build and Test') {
            steps {
                dir (".") {
                    sh './gradlew clean build -Pweblogic --refresh-dependencies -Drelease_version=' + params.RELEASE_VERSION + '  -Dartifact_version=' + params.ARTIFACT_VERSION +'  -Dstage=' + params.STAGE
                    echo('3P Scan Started.')
                    sh 'jar tf ./build/libs/platoref-5.4.0.war|grep "^WEB-INF/lib/">dependencies.txt'
                    sh 'cat dependencies.txt'
                    sed -i -e 's/WEB-INF\/lib\///g' dependencies.txt
                    sed -i -e 's/\///g' dependencies.txt
                    echo('3P Scan Completed.')
//					  sh './gradlew -Dhttp.proxyHost=www-proxy-hqdc.us.oracle.com -Dhttp.proxyPort=80 -Dhttps.proxyHost=www-proxy-hqdc.us.oracle.com -Dhttps.proxyPort=80 -DsystemProp.http.proxyHost=www-proxy-hqdc.us.oracle.com -DsystemProp.https.proxyHost=www-proxy-hqdc.us.oracle.com -DsystemProp.http.proxyPort=80 -DsystemProp.https.proxyPort=80 clean build -Pweblogic --refresh-dependencies'
                }              
            }
        }

  /*
        stage('Static Code Analysis') {
            steps {
                withSonarQubeEnv('ALMSonar') { 
                    dir (".") {
                        sh './gradlew -Dhttp.proxyHost=www-proxy.us.oracle.com -Dhttp.proxyPort=80 -Dhttps.proxyHost=www-proxy.us.oracle.com -Dhttps.proxyPort=80 -DsystemProp.http.proxyHost=www-proxy.us.oracle.com -DsystemProp.https.proxyHost=www-proxy.us.oracle.com -DsystemProp.http.proxyPort=80 -DsystemProp.https.proxyPort=80 sonarqube'
//                        sh './gradlew -Dhttp.proxyHost=www-proxy-hqdc.us.oracle.com -Dhttp.proxyPort=80 -Dhttps.proxyHost=www-proxy-hqdc.us.oracle.com -Dhttps.proxyPort=80 -DsystemProp.http.proxyHost=www-proxy-hqdc.us.oracle.com -DsystemProp.https.proxyHost=www-proxy-hqdc.us.oracle.com -DsystemProp.http.proxyPort=80 -DsystemProp.https.proxyPort=80 sonarqube'
                    }
                }
            }
        }
  */
 
        stage('Publish to Artifactory') {
            /*
            when {
                branch 'master'
            }
            */
            steps {
                script {
                    //rtGradle.tool = 'gradle-3.5'
                    rtGradle.useWrapper = true
                    rtGradle.deployer repo: 'fsgbubanking-gradle-dev-local', server: server
                    rtGradle.resolver repo: 'jcenter', server: server
                    rtGradle.deployer.deployMavenDescriptors = true
                    buildInfo = rtGradle.run rootDir: ".", buildFile: 'build.gradle', tasks: 'clean artifactoryPublish -Pweblogic -Drelease_version=' + params.RELEASE_VERSION + '  -Dartifact_version=' + params.ARTIFACT_VERSION +'  -Dstage=' + params.STAGE 
                    server.publishBuildInfo buildInfo
                }
            }
        }
    }
    post {
        success {
            emailext (
                from:"FSGBU Banking DevOps <fsgbu-banking-devops-noreply@oracle.com>",
                to:"sreenivasan.arumugam@oracle.com",
                subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                    <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
            )
        }
        failure {
            emailext (
                from:"FSGBU Banking DevOps <fsgbu-banking-devops-noreply@oracle.com>",
                to:"sreenivasan.arumugam@oracle.com",
                subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                    <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
                attachLog: true                        
            )
        }
    }    
}
 
def getBuildStatus() {
 echo currentBuild.result
    (currentBuild.result == null) ? "SUCCESS" : currentBuild.result
}
 
def isMasterBuild() {
    if (env.JOB_NAME == null || env.BRANCH == null) {
        return false
    }
    return (env.BRANCH.equals("master"))
}
