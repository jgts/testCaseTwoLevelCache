# Two Level Cache

test case application for interview in Wiley 

Стандартное тестовое задание (реализацтя двух уровневого кэша) которое дают перед собеседованием в wiley. Так же прилагаю замечания по коду (со своими пояснениями и исправлениям).
  *  mix of boxed and primitives - исправлено
  *   inaccurate work with generics -ничего не могу сказать по этому поводу
  *   due to mix of primitive/boxed there are places when NPE will happen for some configs - см первый пункт
  *   formatting - на вкус и цвет.
  *   strange builder for configuration usually we have a constructor in main class with all arguments - Не понравилось использование Bulder шаблона проектирования для конфигурации. На мой взляд странное замечание.
  *   streams are closed in try - Действительно косяк, переписал на try-cath-resource
  *   can't add value for existing key. BTW it will store new value but in file cache so on consequence calls we will return old value from memory cache -  да была логическая ошибка  
  *   containsKey in MRUCacheManager always returns false - исправлено
  *   there are tests but they are hard to read - возможно
  *   Mix of synchronized and Concurrent - оставил HashMap c synchronized
  
  

 
