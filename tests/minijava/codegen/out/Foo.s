.source Test.mj
.class public Foo
.super java/lang/Object
.method public <init>()V
.limit stack 100
.limit locals 100
aload_0
invokespecial java/lang/Object/<init>()V
;getstatic java/lang/System/out Ljava/io/PrintStream;
;ldc 99
;invokevirtual java/io/PrintStream/println(I)V
return
.end method
.method public Foo/foo()I
.limit stack 100
.limit locals 100
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 78
invokevirtual java/io/PrintStream/println(I)V
ldc 5
ireturn
.end method
