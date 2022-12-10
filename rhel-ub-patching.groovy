pipeline {
    agent any    
    stages {
      stage('checkout') {
           steps {
                git branch: 'main', url: 'https://github.com/jdarwin/jenkins-ansible-linux-patching.git'             
          }
        }
          stage('Ansible Stage Starting') {
            steps {
                script {       
                 def tfHome = tool name: 'Ansible'
                 env.PATH = "${tfHome}:${env.PATH}"
                 sh 'ansible --version'   
            }
          }
        }
        stage('Ansible Deploy') {
            steps {
               sh "ansible-playbook --user vagrant rhel-ub-patching.yaml"
      }
    }
  }
}

