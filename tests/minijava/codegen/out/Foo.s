.source Test.mj
.class public Foo
.super java/lang/Object
.field public a I
.method public <init>()V
.limit stack 100
.limit locals 100
aload_0
invokespecial java/lang/Object/<init>()V
return
.end method
.method public foo()I
.limit stack 100
.limit locals 100
getstatic java/lang/System/out Ljava/io/PrintStream;
aload_0
getfield Foo/a I
invokevirtual java/io/PrintStream/println(I)V
ldc 5
ireturn
.end method
.method public boo()I
.limit stack 100
.limit locals 100
iconst_1
areturn
.end method
