# Two Level Cache

standart test case application for interview 

  *  mix of boxed and primitives 
  *   inaccurate work with generics 
  *   due to mix of primitive/boxed there are places when NPE will happen for some configs 
  *   formatting 
  *   strange builder for configuration usually we have a constructor in main class with all arguments 
  *   streams are closed in try 
  *   can't add value for existing key. BTW it will store new value but in file cache so on consequence calls we will return old value from memory cache 
  *   containsKey in MRUCacheManager always returns falseо
  *   there are tests but they are hard to read 
  *   Mix of synchronized and Concurrent
  
  

 
