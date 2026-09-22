package software.amazon.lambda.powertools.tracing.opentelemetry.provider;

import static org.assertj.core.api.Assertions.assertThat;

import io.opentelemetry.context.propagation.TextMapGetter;
import java.util.Map;
import org.junit.jupiter.api.Test;
import software.amazon.lambda.powertools.tracing.opentelemetry.context.TraceContextPropagationMode;

class OpenTelemetryProviderTest {

    @Test
    void shouldProvideTextMapGetter() {
        TextMapGetter<Map<String, String>> getter = OpenTelemetryProvider.textMapGetter();

        assertThat(getter).isNotNull();

        Map<String, String> carrier = Map.of("key1", "value1", "key2", "value2");
        assertThat(getter.get(carrier, "key1")).isEqualTo("value1");
        assertThat(getter.get(carrier, "unknown")).isNull();
        assertThat(getter.get(null, "key1")).isNull();
        assertThat(getter.keys(carrier)).containsExactlyInAnyOrder("key1", "key2");
        assertThat(getter.keys(null)).isEmpty();
    }

    @Test
    void shouldProvidePropagator() {
        assertThat(OpenTelemetryProvider.propagator()).isNotNull();
    }

    @Test
    void shouldProvideTracer() {
        assertThat(OpenTelemetryProvider.tracer()).isNotNull();
    }

    @Test
    void shouldProvideObjectMapper() {
        assertThat(OpenTelemetryProvider.objectMapper()).isNotNull();
    }

    @Test
    void shouldProvideTraceContextPropagationMode() {
        assertThat(OpenTelemetryProvider.traceContextPropagationMode())
                .isEqualTo(TraceContextPropagationMode.PARENT);
    }

    @Test
    void shouldForceFlush() {
        assertThat(OpenTelemetryProvider.forceFlush()).isNotNull();
    }
}
