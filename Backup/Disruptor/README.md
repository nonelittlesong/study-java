## 一、 Disruptor默认是多生产者
需要手动设置为  
`disruptor = new Disruptor<>(factory, 4, executor, ProducerType.SINGLE, new YieldingWaitStrategy());`  
否则会因为竞争造成严重的卡顿  
