import os
import sys
import platform
import requests
import re                                           #RegEx - Needed for getting CSS Fonts
from bs4 import BeautifulSoup
import urllib                                       #USED FOR IMAGE_GRAB


#import lxml.html                                   #NOT BEING USED (Parser)
#from lxml.cssselect import CSSSelector             #NOT BEING USED (Parser)


def getValidTargetURL():
    validURL = False
    while not validURL:
        try:
            if len(sys.argv) >= 3:
                targetURL = str(sys.argv[1])
                targetSite = str(sys.argv[2])
            else:
                print 'Please Enter a valid URL and/or SiteName'
                raise Exception
##            targetURL = raw_input('ENTER URL >> ')
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
##                targetSite = raw_input('ENTER NAME >> ')
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
    
    fileData = "".join(line.replace('\n', '') for line in fileData.split("\n")) #GETS RID OF WHITESPACE
    #Rewrite file with correct spacing #
    print fileData
    temp = ''
    for char in fileData:
        temp+=char
        if char == '>':
            temp+='\t\n'
    print temp
    
    outputFile = open('{}{}.html'.format(workingDir, targetSite), 'w+')
    outputFile.write(str(fileData))
    outputFile.close()
    
def baseURL(targetURL):
    urlList = targetURL.split('/')
    baseURL = '{}//{}'.format(urlList[0], urlList[2])
    return baseURL
    
def scrapeSite(targetURL, targetSite):
    website = requests.get(targetURL)

    data = website.text
    soup = BeautifulSoup(data, 'lxml')

    # Get CSS Files #
    for index, css in enumerate(soup.find_all('link')):
        try:
            # Find CSS #
            if css.get('href') == None or str(css.get('rel')[0]) != 'stylesheet':
                continue
            
            urlList = css.get('href').split('/')
            cssFileName = urlList[len(urlList) - 1]
            if '?' in cssFileName:
                cssFileName = cssFileName.split('?')[0]
            if '.css' not in cssFileName:
                cssFileName = '{}.css'.format(cssFileName)
            print cssFileName
            
            if css.get('href')[0] == '/':
                cssData = requests.get(baseURL(targetURL) + css.get('href')).text
                
            elif str(css.get('href')).startswith('http'):
                cssData = requests.get(css.get('href')).text
            else:
                print 'CSS File Format Not Recognised'
                #print(css.get('href'))

            # Scrape fonts #
            cssLocation = css.get('href').replace(cssFileName, '')
            fontList = re.findall('@font\-face(.*?)\}', (' ').join(cssData.split("\n")))
            for font in fontList:
                fontFiles = re.findall('url\((.*?)\)', font)
                for fontFile in fontFiles:
                    fontFile = fontFile.replace('"', '')
                    if len(fontFile) <= 50:         # Quick Fix

                        # Add Correct Font Link #
                        print cssLocation
                        if fontFile.startswith('/'):
                            print '{}{}'.format(baseURL(targetURL), fontFile)
                            cssData = cssData.replace(fontFile, '{}{}'.format(baseURL(targetURL), fontFile))
                        else:
                            cssData = cssData.replace(fontFile, '{}{}{}'.format(baseURL(targetURL), cssLocation, fontFile))
                            print '{}{}{}'.format(baseURL(targetURL), cssLocation, fontFile)
            
            cssFilePath = '{}\\{}\\styles\\{}'.format(os.getcwd(), targetSite, cssFileName)
            indexedFileName = cssFileName

            # Rewrite (Enumerate) #
            if os.path.isfile(cssFilePath):
                indexedFileName = '{}_{}'.format(str(index), cssFileName)
                cssFilePath = '{}\\{}\\styles\\{}'.format(os.getcwd(), targetSite, indexedFileName)

            cssFile = open(cssFilePath, 'w')
            cssFile.write(cssData.encode('utf-8'))
            cssFile.close()
            
            css['href'] = 'styles/{}'.format(indexedFileName)
        except:
            continue

    # Get JavaScript #
    for js in soup.find_all('script'):
        try:
            if js.get('src') == None:
                continue
            
            urlList = js.get('src').split('/')
            jsFileName = urlList[len(urlList) - 1]
            if '?' in jsFileName:
                jsFileName = jsFileName.split('?')[0]
            if '.js' not in jsFileName:
                jsFileName = '{}.js'.format(cssFileName)
            #print jsFileName
                                   
            jsFile = open('{}\\{}\\javascript\\{}'.format(os.getcwd(), targetSite, jsFileName), 'w')

            #print jsFileName; print js ;print js.get('src'); print soup.find_all('script')
            if str(js.get('src')).startswith('//'):
                jsData = requests.get('https:' + js.get('src')).text
            elif str(js.get('src'))[0] == '/':
                jsData = requests.get(baseURL(targetURL) + js.get('src')).text
            elif str(js.get('src')).startswith('http'):
                jsData = requests.get(js.get('src')).text
            else:
                print "JS File Format Not Recognised"

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
        except:
            continue

    # Get Images #
    for index, img in enumerate(soup.find_all('img')):
        try:
            #print img.get('src')
            if img.get('src').startswith('//'):
                #print baseURL(targetURL).split('/')[0] + img.get('src')
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
        except:
            continue
    
    #print soup#.encode('utf-8')
    return soup.encode('utf-8')#.prettify().encode('utf-8')

# ---------- MAIN ---------- #


targetURL, targetSite = getValidTargetURL()
createLocalFiles(targetSite)
fileData = scrapeSite(targetURL, targetSite)
writeHTMLData(fileData, targetSite)
