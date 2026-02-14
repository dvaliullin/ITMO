# Author = Valiullin Daniil Ravilevich
# Group = P3131
# Date = 01.11.2025

import re

pattern = '\b([а-яА-ЯёЁ]+)(\s+\1)+\b'

test_1 = "Очень очень часто люди допускают опечатки опечатки в тексте тексте."
print(re.sub(pattern,r'\1',test_1,0,re.IGNORECASE))

test_2 = "Он сказал: 'Нет нет', а она ответила: 'Да да!'"
print(re.sub(pattern,r'\1',test_2,0,re.IGNORECASE))

test_3 = "Хор хоровод и хор хористов пели в хоре хоре."
print(re.sub(pattern,r'\1',test_3,0,re.IGNORECASE))

test_4 = "Я не не знаю, куда идти идти дальше дальше."
print(re.sub(pattern,r'\1',test_4,0,re.IGNORECASE))

test_5 = "Да да да, нет нет нет, может быть быть быть."
print(re.sub(pattern,r'\1',test_5,0,re.IGNORECASE))


