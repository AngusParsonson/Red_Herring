import os
import sys
import platform
import requests
import re                                           #RegEx - Needed for getting Valid URL format
from bs4 import BeautifulSoup
import urllib                                       #USED FOR IMAGE_GRAB

#import lxml.html                                   #NOT BEING USED (Parser)
#from lxml.cssselect import CSSSelector             #NOT BEING USED (Parser)

# Test commit
def getValidTargetURL():
    validURL = False
    while not validURL:
        try:
            targetURL = raw_input('ENTER URL >> ')
            # Check for http(s):// #
##            if 'http://' not in targetURL and 'https://' not in targetURL and 'www.' not in targetURL:
##                targetURL = 'http://www.{}'.format(targetURL)
##            elif 'www.' in targetURL and 'http' not in targetURL:
##                targetURL = 'http://{}'.format(targetURL)
##            elif 'http://' in targetURL and 'www.' not in targetURL or 'https://' in targetURL and 'www.' not in targetURL:
##                targetURL = targetURL.split('//')
##                targetURL = '{}//www.{}'.format(targetURL[0], targetURL[1])
            # Check if URL exists #
            print 'Connecting to {}'.format(targetURL)
            connection = requests.get(targetURL)
            if connection.status_code == 200:
                print 'Connection Successful'
                validURL = True
                # Format TargetSite Var #
                targetSite = raw_input('ENTER NAME >> ')
                # Return TargetURL and TargetSite #
                return targetURL, targetSite
            elif connection.status_code != 200:
                print 'Status Code: {}'.format(connection.status_code)
            else:
                raise Exception
                
        except:
            print 'Connection to {} Failed. Enter another URL'.format(targetURL)  #CHANGE MESSAGE
            validURL = False

    
def createLocalFiles(targetSite):
    # Create Dir for TargetSite #
    if not os.path.isdir('{}\\{}'.format(os.getcwd(), targetSite)):
        os.popen('mkdir {}'.format(targetSite)).read()
        os.popen('mkdir {}\\styles'.format(targetSite)).read()
        os.popen('mkdir {}\\images'.format(targetSite)).read()
        os.popen('mkdir {}\\javascript'.format(targetSite)).read()
        

def writeHTMLData(fileData, targetSite):
    # Get Working Directory # 
    workingDir = '{}\\{}\\'.format(os.getcwd(), targetSite)
    # Write HTML to File #
    
    outputFile = open('{}{}.html'.format(workingDir, targetSite), 'w')
    #print fileData
    outputFile.write(str(fileData))
    outputFile.close()
    
def baseURL(targetURL):
    urlList = targetURL.split('/')
    return urlList[0] + '//' + urlList[2]
    
def scrapeSite(targetURL, targetSite):
    website = requests.get(targetURL)

    data = website.text
    soup = BeautifulSoup(data, 'html.parser')

    # Get CSS Files #
    for css in soup.find_all('link'):
        # Find CSS #
        if css.get('href') == None or str(css.get('rel')[0]) != 'stylesheet':
            continue
        urlList = css.get('href').split('/')
        cssFileName = urlList[len(urlList) - 1]
        cssFileName = '{}.css'.format(cssFileName.split('.css')[0])                
        #print cssFileName
        
        if css.get('href')[0] == '/':
            cssData = requests.get(baseURL(targetURL) + css.get('href')).text
            
        elif str(css.get('href')).startswith('http'):
            cssData = requests.get(css.get('href')).text
        else:
            print 'CSS File Format Not Recognised'
            print(css.get('href'))

        filePath = '{}\\{}\\styles\\{}'.format(os.getcwd(), targetSite, cssFileName)
        indexedFileName = cssFileName
        index = 1
        while os.path.isfile(filePath):
            indexedFileName = '{}_{}'.format(str(index), fileName)
            filePath = '{}\\{}\\styles\\{}'.format(os.getcwd(), targetSite, indexedFileName)
            index += 1
        
        cssFile = open(filePath, 'w')
        cssFile.write(cssData.encode('utf-8'))
        cssFile.close()
        
        css['href'] = 'styles/{}'.format(indexedFileName)

    # Get JavaScript #
    for js in soup.find_all('script'):

        if js.get('src') == None:
            continue
        
        urlList = js.get('src').split('/')
        jsFileName = urlList[len(urlList) - 1]
        jsFileName = '{}.js'.format(jsFileName.split('.js')[0])
        print jsFileName
                               
        jsFile = open('{}\\{}\\javascript\\{}'.format(os.getcwd(), targetSite, jsFileName), 'w')

        if str(js.get('src')).startswith('//'):
            jsData = requests.get('https:' + js.get('src')).text
        elif str(js.get('src'))[0] == '/':
            jsData = requests.get(baseURL(targetURL) + js.get('src')).text
        elif str(js.get('src')).startswith('http'):
            jsData = requests.get(js.get('src')).text
        else:
            print "JS File Format Not Recognised"
            print(css.get('src'))

        filePath = '{}\\{}\\styles\\{}'.format(os.getcwd(), targetSite, jsFileName)
        indexedFileName = jsFileName
        index = 1
        while os.path.isfile(filePath):
            indexedFileName = '{}_{}'.format(str(index), jsFileName)
            filePath = '{}\\{}\\styles\\{}'.format(os.getcwd(), targetSite, indexedFileName)
            index += 1
            
        jsFile = open('{}\\{}\\javascript\\{}'.format(os.getcwd(), targetSite, indexedFileName), 'w')
        jsFile.write(str(jsData.encode('utf-8')))
        jsFile.close()
        js['src'] = 'javascript/{}'.format(indexedFileName)

    # Get Images #
    for index, img in enumerate(soup.find_all('img')):
        #try:
            print img.get('src')
            if img.get('src').startswith('//'):
                print baseURL(targetURL).split('/')[0] + img.get('src')
                img['src'] = baseURL(targetURL).split('/')[0] + img.get('src')
            elif img.get('src')[0] == '/':
                #print '{}\\{}\\images\\{}'.format(os.getcwd(), targetSite, img.get('src').split('/')[-1])
                if not os.path.isfile('{}\\{}\\images\\{}'.format(os.getcwd(), targetSite, img.get('src').split('/')[-1])):
                    urllib.urlretrieve('{}{}'.format(targetURL, img.get('src')), '{}\\{}\\images\\{}'.format(os.getcwd(), targetSite, img.get('src').split('/')[-1]))

                    img['src'] = 'images/{}{}'.format(os.path.splitext(os.path.basename(img['src']))[0],
                                                      os.path.splitext(os.path.basename(img['src']))[1])
                else:
                    #print 'enumerating!!', index, 'starts with /'
                    urllib.urlretrieve('{}{}'.format(targetURL, img.get('src')), '{}\\{}\\images\\{}_{}'.format(os.getcwd(), targetSite, index, img.get('src').split('/')[-1]))

                    img['src'] = 'images/{}_{}{}'.format(index, os.path.splitext(os.path.basename(img['src']))[0],
                                                                os.path.splitext(os.path.basename(img['src']))[1])
            elif img.get('src')[0] == 'h':
                if not os.path.isfile('{}\\{}\\images\\{}'.format(os.getcwd(), targetSite, img.get('src').split('/')[-1])):
                    urllib.urlretrieve('{}'.format(img.get('src')), '{}\\{}\\images\\{}'.format(os.getcwd(), targetSite, img.get('src').split('/')[-1]))

                    img['src'] = 'images/{}{}'.format(os.path.splitext(os.path.basename(img['src']))[0],
                                                      os.path.splitext(os.path.basename(img['src']))[1])
                else:
                    #print 'enumerating!!', index, 'starts with h'
                    urllib.urlretrieve('{}'.format(img.get('src')), '{}\\{}\\images\\{}_{}'.format(os.getcwd(), targetSite, index, img.get('src').split('/')[-1]))

                    img['src'] = 'images/{}_{}{}'.format(index, os.path.splitext(os.path.basename(img['src']))[0],
                                                                os.path.splitext(os.path.basename(img['src']))[1])
            else:
                continue
        #except:
         #   continue
    
    #print soup#.encode('utf-8')
    return soup.encode('utf-8')#.prettify().encode('utf-8')

# ---------- MAIN ---------- #

while True:
    targetURL, targetSite = getValidTargetURL()
    #targetURL = 'https://www.escrow.com/'
    createLocalFiles(targetSite)
    fileData = scrapeSite(targetURL, targetSite)
    writeHTMLData(fileData, targetSite)
