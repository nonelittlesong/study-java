
## ListFragment
ListFragment的默认布局包含一个ListView。  
**1. ListFragment设置Adapter**  
重载`onViewCreated`方法:  
```java
    public static class Demo2Fragment extends ListFragment {
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            SampleTextListAdapter adapter = new SampleTextListAdapter(getActivity());
            setListAdapter(adapter);
        }
    }
```
