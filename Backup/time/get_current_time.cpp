static unsigned long get_current_time(void)
{
    struct timeval tv;

    gettimeofday(&tv, NULL);

    return (tv.tv_sec*1000000 + tv.tv_usec);
}
