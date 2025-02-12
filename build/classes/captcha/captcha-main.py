import os
import sys
from random import randint
from captcha.image import ImageCaptcha

content = sys.argv[1]
print(content)
img = ImageCaptcha(width=280,height=90)
img.write(content,'src/captcha/'+content+'.png')
