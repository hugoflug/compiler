.source Test.mj
.class public Test
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 100
new Foo
dup
invokespecial Foo/<init>()V
astore 1
getstatic java/lang/System/out Ljava/io/PrintStream;
aload 1
invokevirtual Foo/foo()I
invokevirtual java/io/PrintStream/println(I)V
return
.end method
