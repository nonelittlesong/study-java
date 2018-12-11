```java
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new Demo1Fragment();
            } else {
                return new Demo2Fragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.title_demo1);
                case 1:
                    return getString(R.string.title_demo2);
            }
            return null;
        }
    }

    public static class Demo1Fragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_demo1, container, false);

            ((TextView) rootView.findViewById(R.id.sample1).findViewById(R.id.title)).setText("Sample 1");
            ((TextView) rootView.findViewById(R.id.sample2).findViewById(R.id.title)).setText("Sample 2");

            ExpandableTextView expTv1 = (ExpandableTextView) rootView.findViewById(R.id.sample1)
                    .findViewById(R.id.expand_text_view);
            ExpandableTextView expTv2 = (ExpandableTextView) rootView.findViewById(R.id.sample2)
                    .findViewById(R.id.expand_text_view);

            expTv1.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
                @Override
                public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                    Toast.makeText(getActivity(), isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
                }
            });

            expTv1.setText(getString(R.string.dummy_text1));
            expTv2.setText(getString(R.string.dummy_text2));

            return rootView;
        }
    }

    public static class Demo2Fragment extends ListFragment {
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            SampleTextListAdapter adapter = new SampleTextListAdapter(getActivity());
            setListAdapter(adapter);
        }
    }
```
