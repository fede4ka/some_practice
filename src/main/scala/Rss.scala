import java.net.URL
import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import scala.collection.JavaConverters._

object Rss extends App {
   //Тренды гугла/US
    val feedUrlg = new URL("https://trends.google.com/trends/trendingsearches/daily/rss?geo=US")
    val inputg = new SyndFeedInput
    val feedg: SyndFeed = inputg.build(new XmlReader(feedUrlg))
    val entriesg = asScalaBuffer(feedg.getEntries).toVector
    val titlesFromGoogle = entriesg.map (_.getTitle).flatMap(_.split("\\W+"))

    //Новости CNN/US
    val feedUrl = new URL("http://rss.cnn.com/rss/edition_us.rss")
    val input = new SyndFeedInput
    val feed: SyndFeed = input.build(new XmlReader(feedUrl))
    val entries = asScalaBuffer(feed.getEntries).toVector

    for (entry <- entries) {
       if (titlesFromGoogle.exists(entry.getTitle.contains))
        {
          println("Title: " + entry.getTitle)
          println("URL:   " + entry.getUri)
          println("Date:  " + entry.getPublishedDate)
          println()
        }
    }
}
