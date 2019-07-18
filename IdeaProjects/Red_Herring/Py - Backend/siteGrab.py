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
            if 'http://' not in targetURL and 'https://' not in targetURL and 'www.' not in targetURL:
                targetURL = 'http://www.{}'.format(targetURL)
            elif 'www.' in targetURL and 'http' not in targetURL:
                targetURL = 'http://{}'.format(targetURL)
            elif 'http://' in targetURL and 'www.' not in targetURL or 'https://' in targetURL and 'www.' not in targetURL:
                targetURL = targetURL.split('//')
                targetURL = '{}//www.{}'.format(targetURL[0], targetURL[1])
            # Check if URL exists #
            print 'Connecting to {}'.format(targetURL)
            connection = requests.get(targetURL)
            if connection.status_code == 200:
                print 'Connection Successful'
                validURL = True
                # Format TargetSite Var #
                targetSite = targetURL.split('/')[-1]
                targetSite = targetSite.split('.')[1]
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
    outputFile.write(str(fileData))
    outputFile.close()

    
def scrapeSite(targetURL, targetSite):
    website = requests.get(targetURL)

    data = website.text
    soup = BeautifulSoup(data, 'html.parser')

    # Get CSS Files #
    for css in soup.find_all('link'):
        # Find CSS #
        try:
            if css.get('rel')[0] == 'stylesheet':
                if css.get('href')[0] == '/':
                    cssPage = requests.get('{}{}'.format(targetURL, css.get('href')))
                    cssData = cssPage.text
                    cssSoup = BeautifulSoup(cssData, 'html.parser')

                    cssFile = open('{}\\{}\\styles\\{}'.format(os.getcwd(), targetSite, css.get('href').split('/')[-1]), 'w')
                    cssFile.write(cssSoup.prettify().encode('utf-8'))
                    cssFile.close()
                else:
                    continue
                css['href'] = 'styles/{}{}'.format(os.path.splitext(os.path.basename(css['href']))[0],
                                                   os.path.splitext(os.path.basename(css['href']))[1])
            else:
                continue
        except:
            continue

    # Get JavaScript #
    for js in soup.find_all('script'):
        #try:
        print str(js.get('src')), type(str(js.get('src')))
        if str(js.get('src'))[0] == '/' and str(js.get('src'))[1] != '/':
            jsPage = requests.get('{}{}'.format(targetURL, js.get('src')))
            jsData = jsPage.text

            jsFile = open('{}\\{}\\javascript\\{}'.format(os.getcwd(), targetSite, js.get('src').split('/')[-1]), 'w')
            jsFile.write(str(jsData.encode('utf-8')))
            jsFile.close()
        elif str(js.get('src'))[0] == '/' and str(js.get('src'))[1] == '/':
            #print js.get('src')[2:]
            jsPage = requests.get('http://{}'.format(js.get('src')[2:]))
            jsData = jsPage.text
            print js.get('src').split('/')[-1]
            jsFile = open('{}\\{}\\javascript\\{}'.format(os.getcwd(), targetSite, js.get('src').split('/')[-1]), 'w')
            jsFile.write(str(jsData.encode('utf-8')))
            jsFile.close()
        elif str(js.get('src'))[0] == 'h':
            jsPage = requests.get('{}'.format(js.get('src')))
            jsData = jsPage.text

            jsFile = open('{}\\{}\\javascript\\{}'.format(os.getcwd(), targetSite, js.get('src').split('/')[-1]), 'w')
            jsFile.write(str(jsData.encode('utf-8')))
            jsFile.close()
        else:
            continue
        #except:
         #   continue
        js['src'] = 'javascript/{}{}'.format(os.path.splitext(os.path.basename(js['src']))[0],
                                             os.path.splitext(os.path.basename(js['src']))[1])
        print js['src'], type(js['src'])

    # Get Images #
    for img in soup.find_all('img'):
        try:
            if img.get('src')[0] == '/':
                urllib.urlretrieve('{}{}'.format(targetURL, img.get('src')), '{}\\{}\\images\\{}'.format(os.getcwd(), targetSite, img.get('src').split('/')[-1]))
            elif img.get('src')[0] == 'h':
                urllib.urlretrieve('{}'.format(img.get('src')), '{}\\{}\\images\\{}'.format(os.getcwd(), targetSite, img.get('src').split('/')[-1]))
            else:
                continue
        except:
            continue
        img['src'] = 'images/{}{}'.format(os.path.splitext(os.path.basename(img['src']))[0],
                                          os.path.splitext(os.path.basename(img['src']))[1])
        
    return soup.prettify().encode('utf-8')

# ---------- MAIN ---------- #

while True:
    targetURL, targetSite = getValidTargetURL()
    #targetURL = 'https://groceries.morrisons.com'
    createLocalFiles(targetSite)
    fileData = scrapeSite(targetURL, targetSite)
    writeHTMLData(fileData, targetSite)

