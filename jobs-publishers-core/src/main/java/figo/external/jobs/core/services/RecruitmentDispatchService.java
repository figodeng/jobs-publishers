package figo.external.jobs.core.services;

import figo.external.jobs.core.models.Recruitment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

@Component
public class RecruitmentDispatchService {
    private static Set<SubscribeService> subscribers = new HashSet<>();

    public RecruitmentDispatchService(@Autowired List<SubscribeService> subscribeServices) {
        subscribers.addAll(subscribeServices);
    }

    public void subscribe(SubscribeService subscriber) {
        subscribers.add(subscriber);
    }

    public void cancelSubscribe(SubscribeService subscriber) {
        subscribers.remove(subscriber);
    }

    public void update(Recruitment recruitment) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                4,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));

        List<Future> futures = new ArrayList<>();
        for (SubscribeService subscriber : subscribers) {
            futures.add(threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    subscriber.subscribe(recruitment);
                }
            }));
        }

        for (Future future : futures) {
            future.get();
        }
        threadPoolExecutor.shutdown();
    }
}
