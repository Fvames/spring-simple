package dev.fvames.thinking.in.spring.resource;

import dev.fvames.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.stream.Stream;

public class ResourcePatternResolverDemo {
    public static void main(String[] args) throws IOException {
        String path = "classpath*:/META-INF/*";
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = patternResolver.getResources(path);
        Stream.of(resources).filter(ResourceUtils::isMatchCurrentProject).map(ResourceUtils::getContent).forEach(System.out::println);
    }
}
