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
ldc 78
invokevirtual java/io/PrintStream/println(I)V
aload_0
invokevirtual Foo/boo()I
ifeq l4
ldc 3
putfield ClassTable{
methods={
boo = MethodTable{
params={
}
fields={
}
}

foo = MethodTable{
params={
}
fields={
}
}

}
fields={
a = Int{}
}
}/a I
l4:
getstatic java/lang/System/out Ljava/io/PrintStream;
getfield Foo/a I
ldc 2
imul
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
