package kg.coder.placeholderproject.ui;

public interface LifeCycle<V> {
    void bind(V view);
    void unbind();
}