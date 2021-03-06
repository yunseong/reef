﻿// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
// 
//   http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

using Newtonsoft.Json;

namespace Org.Apache.REEF.Client.YARN.RestClient.DataModel
{
    /// <summary>
    /// Class generated based on schema provided in
    /// <see cref="!:http://hadoop.apache.org/docs/r2.6.0/hadoop-yarn/hadoop-yarn-site/WebServicesIntro.html">
    /// Hadoop RM REST API </see> documentation.
    /// </summary>
    internal sealed class AmContainerSpec
    {
        [JsonProperty(PropertyName = "local-resources")]
        public LocalResources LocalResources { get; set; }

        [JsonProperty(PropertyName = "environment")]
        public Environment Environment { get; set; }

        [JsonProperty(PropertyName = "commands")]
        public Commands Commands { get; set; }

        [JsonProperty(PropertyName = "service-data")]
        public ServiceData ServiceData { get; set; }

        [JsonProperty(PropertyName = "credentials")]
        public Credentials Credentials { get; set; }

        [JsonProperty(PropertyName = "application-acls")]
        public Acls ApplicationAcls { get; set; }
    }
}