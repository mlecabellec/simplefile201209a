dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            //url = "jdbc:h2:mem:simplefile201209a;MVCC=TRUE;LOCK_TIMEOUT=600"
            url = "jdbc:h2:mem:simplefile201209a;MVCC=TRUE;LOCK_TIMEOUT=1"
            //url = "jdbc:h2:file:simplefile201209a;MVCC=TRUE;LOCK_TIMEOUT=600"
        }
        dataSource_keep {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:file:simplefile201209a;MVCC=TRUE;LOCK_TIMEOUT=1"
        }        
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:simplefile201209a;MVCC=TRUE;LOCK_TIMEOUT=1"
            //url = "jdbc:h2:file:simplefile201209a;MVCC=TRUE;LOCK_TIMEOUT=600"
        }
        dataSource_keep {
            dbCreate = "update"
            url = "jdbc:h2:file:simplefile201209a;MVCC=TRUE;LOCK_TIMEOUT=1"
        }        
    }
    production {
        dataSource {
            dbCreate = "update"
            //url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            url = "jdbc:h2:mem:simplefile201209a;MVCC=TRUE;LOCK_TIMEOUT=1"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
        dataSource_keep {
            dbCreate = "update"
            url = "jdbc:h2:simplefile201209a;MVCC=TRUE;LOCK_TIMEOUT=1"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }        
    }
}
