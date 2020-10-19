# Tweet Analysis

## Getting Started

```
This Project streams real time Tweets every Second and analyzes the following
1. Total number of Tweets received
2. Total number of Tweets containing urls
3. Average number of Tweets received per second
4. Average number of Tweets received per minute
5. Average number of Tweets received per hour
6. Top 20 hashTags
7.  Top 20 Urls
```

### Prerequisite

Install and start Kafka Locally

```
brew install kafka

zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties

kafka-server-start /usr/local/etc/kafka/server.properties

$Creating a Kafka Topic
kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

$Star the consumer to view twiter stream in your test topic
kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning
```


Clone the GitHib Repository in your local IDE

If no IDE is present in your machine you can install community edition of  Intellij IDE here https://www.jetbrains.com/idea/download/#section=mac


## Optimizations

Can use Kafka Offset or BatchId for exactly once Semantics



Example Outputs for Some Batches

Run streamToKafka class to load twitter data into kafka and then Run processStream for Analysis


```

-------------------------------------------
Batch: 0
-------------------------------------------
+-----------+---------+-------------------+------------------+-------------------+-----------------+
|tweet_count|url_count|avg_tweets_per_sec |avg_tweets_per_min|avg_tweets_per_hour|url_tweets_perc  |
+-----------+---------+-------------------+------------------+-------------------+-----------------+
|8863       |2713     |0.02049784105442578|1.2298704632655468|73.7922277959328   |30.61040279814961|
+-----------+---------+-------------------+------------------+-------------------+-----------------+

-------------------------------------------
Batch: 1
-------------------------------------------
+-----------+---------+--------------------+------------------+-------------------+-----------------+
|tweet_count|url_count|avg_tweets_per_sec  |avg_tweets_per_min|avg_tweets_per_hour|url_tweets_perc  |
+-----------+---------+--------------------+------------------+-------------------+-----------------+
|9225       |2815     |0.021334609940887518|1.2800765964532512|76.80459578719507  |30.51490514905149|
+-----------+---------+--------------------+------------------+-------------------+-----------------+

-------------------------------------------
Batch: 2
-------------------------------------------
+-----------+---------+--------------------+------------------+-------------------+-----------------+
|tweet_count|url_count|avg_tweets_per_sec  |avg_tweets_per_min|avg_tweets_per_hour|url_tweets_perc  |
+-----------+---------+--------------------+------------------+-------------------+-----------------+
|10066      |3055     |0.023278563417087752|1.396713805025265 |83.8028283015159   |30.34969203258494|
+-----------+---------+--------------------+------------------+-------------------+-----------------+

-------------------------------------------
Batch: 0
-------------------------------------------
+-----------------------+---+
|UrlTag                 |cnt|
+-----------------------+---+
|https://…              |12 |
|https://t.co/q2DtIeec5Z|11 |
|https://t.…            |8  |
|https://t.co/Zkqi2Wh0HU|7  |
|https://t.c…           |7  |
|https://t.co/…         |6  |
|https://t.co/aDzVOY0gkg|6  |
|https://t.co/L65rlFx6Yv|5  |
|https://t.co/8BfOf0jP… |5  |
|https://t.co/Rkm6ZU90s0|5  |
|https://t.co/Ow23GJvQVf|5  |
|https://t.co/52TI25DFYM|4  |
|https://t.co/RpEEVtuiot|4  |
|https://t.co/4MIuMOdY7z|4  |
|https://t.co/NZSp8123eQ|4  |
|https://t.co/LR0IzklIiM|4  |
|https://t.co/PKgch98fm5|4  |
|https://t.co/t76aa0HeoG|4  |
|https://t.co/TxiG7n1nmP|4  |
|https://t.co/jfL3VDQ9jN|4  |
+-----------------------+---+

-------------------------------------------
Batch: 1
-------------------------------------------
+-----------------------+---+
|UrlTag                 |cnt|
+-----------------------+---+
|https://…              |12 |
|https://t.co/q2DtIeec5Z|11 |
|https://t.…            |9  |
|https://t.co/…         |8  |
|https://t.co/Zkqi2Wh0HU|7  |
|https://t.c…           |7  |
|https://t.co/aDzVOY0gkg|6  |
|https://t.co/L65rlFx6Yv|5  |
|https://t.co/8BfOf0jP… |5  |
|https://t.co…          |5  |
|https://t.co/Rkm6ZU90s0|5  |
|https://t.co/Ow23GJvQVf|5  |
|https://t.co/52TI25DFYM|4  |
|https://t.co/RpEEVtuiot|4  |
|https://t.co/4MIuMOdY7z|4  |
|https://t.co/NZSp8123eQ|4  |
|https://t.co/LR0IzklIiM|4  |
|https://t.co/PKgch98fm5|4  |
|https://t.co/t76aa0HeoG|4  |
|https://t.co/TxiG7n1nmP|4  |
+-----------------------+---+


-------------------------------------------
Batch: 0
-------------------------------------------
+--------------------------+---+
|hashTag                   |cnt|
+--------------------------+---+
|#ม็อบ17ตุลา                 |78 |
|#EndSARS                  |73 |
|#whatishappeninginthailand|32 |
|#ประยุทธ์ออกไป              |28 |
|#วชิราลงกรณ์เป็นฆาตรกร       |25 |
|#ม็อบ16ตุลา                 |24 |
|#17ตุลาไปราชประสงค์         |20 |
|#ShopeeKasihGalaxyA71     |18 |
|#แบนดาราปรสิต              |17 |
|#FreeJaggiNow             |17 |
|#…                        |13 |
|#PCAs                     |11 |
|#YogiJiPray2SC_For69kOrder|11 |
|#16ตุลาไปแยกปทุมวัน          |10 |
|#LOVEFORLUCAS             |10 |
|#KCAMexico                |9  |
|#SarkaruVaariPaata        |8  |
|#1                        |8  |
|#前澤じゃんけん             |7  |
|#EndPoliceBrutality       |7  |
+--------------------------+---+



-------------------------------------------
Batch: 1
-------------------------------------------
+--------------------------+---+
|hashTag                   |cnt|
+--------------------------+---+
|#EndSARS                  |114|
|#ม็อบ17ตุลา                 |78 |
|#whatishappeninginthailand|32 |
|#ประยุทธ์ออกไป              |28 |
|#วชิราลงกรณ์เป็นฆาตรกร       |25 |
|#ม็อบ16ตุลา                 |24 |
|#17ตุลาไปราชประสงค์         |20 |
|#ShopeeKasihGalaxyA71     |18 |
|#…                        |17 |
|#แบนดาราปรสิต              |17 |
|#FreeJaggiNow             |17 |
|#PCAs                     |11 |
|#YogiJiPray2SC_For69kOrder|11 |
|#16ตุลาไปแยกปทุมวัน          |10 |
|#세븐틴                     |10 |
|#LOVEFORLUCAS             |10 |
|#ม็อบ19ตุลา                 |10 |
|#KCAMexico                |9  |
|#EndPoliceBrutality       |9  |
|#EndSars                  |9  |
+--------------------------+---+


Sample Twitter Feed to Kafka
🚀Join T…
RT @pledis_17: [17'S 조슈아] 캐럿들 이번 활동 많이 기대해주세요! 진짜 열심히 준비한 앨범이니까 많이 사랑해주시고 빨리봐요! https://t.co/9mGYB9fp3O
RT @MrOdanz: Currently live at @999KISSFMABUJA to talk about the #ENDSARS protest in Abuja and Nationwide. A bid to sensitise the public on…
RT @adeyanjudeji: It’s Day 71 of constant protest in Belarus 🇧🇾. We must not give up in Nigeria. https://t.co/ayS5JnWK4T
RT @ElisseGives: 10 gcash. Just follow me and @gwenygives . Retweet and tag friends. Ends in 30 mins.🧡
RT @Bibolotty: manco il #Bingo è stato capace di chiudere.
RT @ToyosiGodwin: Bankers are joining. Lawyers are protesting. Everyone is involved. This protest is making me feel proud.


```

