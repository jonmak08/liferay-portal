## Java package formatting

### Naming

#### Follow file location
The java package name should match the directory structure of the location of
the java file.

#### Example

The correct package for class UserLocalServiceImpl located in
<<<<<<< HEAD
`portal-impl\com\liferay\portal\service\impl\` is
=======
```portal-impl\com\liferay\portal\service\impl\``` is
>>>>>>> compatible

```java
package com.liferay.portal.service.impl;
```

#### Follow modules settings
<<<<<<< HEAD
For modules, the package name should match the `Bundle-SymbolicName` specified
in the bnd settings
=======
For modules, the package name should match the ```Bundle-SymbolicName```
specified in the bnd settings
>>>>>>> compatible

#### Example

```
Bundle-SymbolicName: com.liferay.freemarker.osgi.bridge
```
<<<<<<< HEAD
The package name for all files inside the module should start with
`com.liferay.freemarker.osgi.bridge`

#### Exceptions

1. Bundle-SymbolicName ending with `.api` or `.test`
=======
The package path for all files inside the module should start with
```com.liferay.freemarker.osgi.bridge```

#### Exceptions

1. Bundle-SymbolicName ending with ```.api``` or ```.test```
>>>>>>> compatible

```
Bundle-SymbolicName: com.liferay.blogs.api
```
or
```
Bundle-SymbolicName: com.liferay.blogs.api
```

<<<<<<< HEAD
The package name for all files inside the module should start with
`com.liferay.blogs`

2. Bundle-SymbolicName ending with `.impl`
=======
The package path for all files inside the module should start with
```com.liferay.blogs```

2. Bundle-SymbolicName ending with ```.impl```
>>>>>>> compatible

```
Bundle-SymbolicName: com.liferay.blogs.demo.data.creator.impl
```

<<<<<<< HEAD
The package name for all files inside the module should start with
`com.liferay.blogs.demo.data.creator.impl` or
`com.liferay.blogs.demo.data.creator.internal`

We should not have package names that contain both `impl` and `internal` like
`com.liferay.wiki.internal.util.impl`, because `internal` implies `impl`
=======
The package path for all files inside the module should start with
```com.liferay.blogs.demo.data.creator.impl``` or
```com.liferay.blogs.demo.data.creator.internal```
>>>>>>> compatible
