package com.icebirdtech.dmp.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

import com.icebirdtech.dmp.log.DmpLogger;

public class CacheEventLogger implements CacheEventListener <Object, Object> {

  @Override
  public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
      DmpLogger.logApplicationInfo(cacheEvent.toString());
  }
}