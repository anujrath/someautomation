package utils

class ProjectConfig {
    static String userName="admin"
    static String password="passworda"
    static String https="http://"
    static String urlPart=https + userName + ":" + password + "@"
    static String hostName="localhost:8082"
    static String AppURL=https + hostName
    static String serverPath=hostName + "/artifactory/libs-release-local/doc.pdf"
    static String localFilePath= "src/test/resources/doc.pdf"
}
