package io.qameta.allure.core;

import io.qameta.allure.entity.Attachment;
import io.qameta.allure.entity.TestResult;

import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Contains parsed results.
 *
 * @since 2.0
 */
public interface LaunchResults {

    /**
     * Returns not hidden test results.
     *
     * @return the results that are not hidden.
     */
    default Set<TestResult> getResults() {
        return getAllResults().stream()
                .filter(result -> !result.isHidden())
                .collect(Collectors.toSet());
    }

    /**
     * Returns all test results, including hidden.
     *
     * @return all test results.
     */
    Set<TestResult> getAllResults();

    /**
     * Returns all attachments.
     *
     * @return attachments.
     */
    Map<Path, Attachment> getAttachments();

    /**
     * Returns extra info by given name.
     *
     * @param name the name of extra block to return.
     * @param <T>  the java type of extra block.
     * @return the found block or empty if not present.
     */
    <T> Optional<T> getExtra(String name);

    /**
     * Shortcut for {@link #getExtra(String)}. Returns default value instead of empty optional
     * if block not present.
     *
     * @param name         the name of extra block to return.
     * @param defaultValue the supplier of default value.
     * @param <T>          the java type of extra block.
     * @return the found block or default value.
     */
    default <T> T getExtra(String name, Supplier<T> defaultValue) {
        final Optional<T> extra = getExtra(name);
        return extra.orElseGet(defaultValue);
    }

}