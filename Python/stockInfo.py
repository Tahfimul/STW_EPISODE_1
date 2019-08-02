import requests
from bs4 import BeautifulSoup
import json
import time
from firebase import firebase

firebase = firebase.FirebaseApplication('LINK_HERE', None)

totalReqs = 0;

def incrementTotalReqs():
    global totalReqs
    totalReqs+=1

tickerIndex =0

def incrementTickerIndex():
    global tickerIndex
    tickerIndex +=1

def resetTickerIndex():
    global tickerIndex
    tickerIndex = 0

tickers = ['BABA', 'GOOG', 'FB', 'SNAP', 'AMZN']

def postTickerUpdate(ticker, category, val):
    category = category.replace(".", " DOT ")
    val = val.replace(".", " DOT ")
    print(ticker+" Update "+category+" "+val)
    firebase.put("Updates/", name="ticker", data=ticker)
    firebase.put("Updates/", name=category, data=val)

def postTicker(ticker, category, val):
    category = category.replace(".", " DOT ")
    val = val.replace(".", " DOT ")
    print(ticker+" "+category+" "+val)
    firebase.put("Tickers/"+ticker, name=category, data=val)

def unpackLatestTimestamp(ticker, timestamp, type):
    for category in timestamp.keys():
        if type is 0:
            postTicker(ticker, category, timestamp[category])
        else:
            postTicker(ticker, category, timestamp[category])
            postTickerUpdate(ticker, category, timestamp[category])

def unpackTimeSeries(ticker, timeSeries, type):
    timestamps = list(timeSeries.keys())
    unpackLatestTimestamp(ticker, timeSeries[timestamps[0]], type)

while tickerIndex<5:
    url = 'https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol='+tickers[tickerIndex]+'&interval=1min&apikey=YOUR_API_KEY_HERE'
    res = requests.get(url)
    content = json.loads(res.text)
    unpackTimeSeries(tickers[tickerIndex], content["Time Series (1min)"], 0)
    incrementTickerIndex()
resetTickerIndex()
time.sleep(40)

while totalReqs<455:
    while tickerIndex<5:
        url = 'https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol='+tickers[tickerIndex]+'&interval=1min&apikey=YOUR_API_KEY_HERE'
        res = requests.get(url)

        content = json.loads(res.text)
        print(content)
        unpackTimeSeries(tickers[tickerIndex], content["Time Series (1min)"], 1)

        incrementTickerIndex()
    resetTickerIndex()
    time.sleep(40)
