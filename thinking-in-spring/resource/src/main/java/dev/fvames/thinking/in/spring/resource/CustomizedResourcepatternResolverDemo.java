package dev.fvames.thinking.in.spring.resource;

import dev.fvames.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 自定义 {@link ResourcePatternResolver}
 *
 * @see ResourcePatternResolver
 * @see PathMatchingResourcePatternResolver
 * @see org.springframework.util.PathMatcher
 */
public class CustomizedResourcepatternResolverDemo {
    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = "/" + System.getProperty("user.dir")
                + "/resource/src/main/java/dev/fvames/thinking/in/spring/resource/*.java";
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        Resource[] resources = patternResolver.getResources(currentJavaFilePath);
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
    }

    static class javaFilePathMatcher implements PathMatcher{

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
 }
