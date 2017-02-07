package c6h2cl2.twitter

import twitter4j.Status
import twitter4j.TwitterStreamFactory
import twitter4j.UserStreamAdapter
import twitter4j.conf.ConfigurationBuilder
import java.io.FileInputStream
import java.util.*
import twitter4j.FilterQuery




/**
 * @author C6H2Cl2
 */
var CONSUMER_KEY = ""
private set
var CONSUMER_SECRET = ""
private set
var ACCESS_TOKEN = ""
private set
var ACCESS_SECRET = ""
private set
private const val property_file = "twitter4j.properties"

fun main(args: Array<String>){
    loadProperty()
    val builder = ConfigurationBuilder()
    builder.setOAuthConsumerKey(CONSUMER_KEY)
            .setOAuthConsumerSecret(CONSUMER_SECRET)
            .setOAuthAccessToken(ACCESS_TOKEN)
            .setOAuthAccessTokenSecret(ACCESS_SECRET)
    val conf = builder.build()
    val stream= TwitterStreamFactory(conf).instance
    JavaWrapper.addStatusListener(stream, KtStreamAdapter())
    val track = arrayOf("プログラミング")
    val filter = FilterQuery()
    filter.track(*track)
    stream.filter(filter)
    //stream.user()
}

private fun loadProperty(){
    val properties = Properties()
    val inputStream = FileInputStream(property_file)
    properties.load(inputStream)
    inputStream.close()
    CONSUMER_KEY = properties.getProperty("oauth.consumerKey")
    CONSUMER_SECRET = properties.getProperty("oauth.consumerSecret")
    ACCESS_TOKEN = properties.getProperty("oauth.accessToken")
    ACCESS_SECRET = properties.getProperty("oauth.accessTokenSecret")
}

private class KtStreamAdapter : UserStreamAdapter() {
    override fun onStatus(status: Status) {
        status.isFavorited
        println("\n\n\n@${status.user.screenName}: ${status.text}\n\n\n")

    }
}