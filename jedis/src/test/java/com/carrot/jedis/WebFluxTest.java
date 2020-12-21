package com.carrot.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: Created by carrot
 * 2020/12/17 15:48
 */
@Slf4j
@SpringBootTest
public class WebFluxTest {

    @Test
    public void testReactor(){
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6, 7);
        Mono<Integer> mono = Mono.just(1);
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7};
        Flux<Integer> arrayFlux = Flux.fromArray(integers);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Flux<Integer> listFlux = Flux.fromIterable(list);
        Flux<Integer> fluxFlux = Flux.from(flux);
        Flux<Integer> streamFlux = Flux.fromStream(Stream.of(1, 2, 3, 4, 5, 6, 7));

        flux.subscribe();
//        arrayFlux.subscribe(System.out::println);
//        listFlux.subscribe(System.out::println,System.err::println);
//        fluxFlux.subscribe(System.out::println,System.err::println,()->System.out.println("complete"));
//        streamFlux.subscribe(System.out::println,
//                System.err::println,
//                ()->System.out.println("complete"),
//                subscription -> subscription.request(3));
//        streamFlux.subscribe(new DemoSubscriber());
//        System.out.println("1-------------------------------------");
//        flux.map(i -> i * 3).subscribe(System.out::println);
//        System.out.println("2-------------------------------------");
//        arrayFlux.flatMap(i -> flux).subscribe(System.out::println);
//        System.out.println("3-------------------------------------");
//        listFlux.filter(i -> i>3).subscribe(System.out::println);
//        System.out.println("4-------------------------------------");
//        Flux.zip(fluxFlux,streamFlux).subscribe(zip -> System.out.println(zip.getT1() + zip.getT2()));

          flux.map(i -> {
              System.out.println(Thread.currentThread().getName() + "-map1");
              return i*3;
          }).publishOn(Schedulers.elastic()).map(
                  i -> {
                      System.out.println(Thread.currentThread().getName() + "-map2");
                      return i/3;
                  }
          ).subscribeOn(Schedulers.parallel()).subscribe(it -> System.out.println(Thread.currentThread().getName() + "-" + it));
    }

    class DemoSubscriber extends BaseSubscriber<Integer>{
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("subscribe");
            subscription.request(1);
        }

        @Override
        protected void hookOnNext(Integer value) {
            // 背压
            if(value == 4){
                cancel();
            }
            System.out.println(value);
            request(1);
        }
    }
}
